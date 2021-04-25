package sbet;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

/**
 * @Author ISJINHAO
 * @Date 2021/1/8 9:39
 */
public class TomcatBootStrapWithoutDefaultConfig {

    public static void main(String[] args) throws LifecycleException {

        // 获取类加载器的基本路径
        String baseDir = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println("baseDir  : " + baseDir);

        // 启动 tomcat
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(createTomcatBaseDir());

        /**
         * 默认的Servlet有两个，一个是 DefaultServlet，用于处理静态页面；
         * 一个是 JspServlet，用于处理JSP页面。
         * 不要加入默认的 Servlet，因为默认的 Servlet 会包含JSP相关的 Servlet。
         * 而对于DefaultServlet，Springboot并没有采用这个方式来提供静态页面服务，而是基于DispatchServlet进行了自己的url Mapping
         */
        tomcat.setAddDefaultWebXmlToWebapp(false);

        tomcat.setPort(Integer.getInteger("port", 8080));
        tomcat.getConnector();

        // 创建 WebApp，contextPath就是url路径前缀
        Context context = tomcat.addWebapp("", baseDir);

        context.setParentClassLoader(TomcatBootStrapWithoutDefaultConfig.class.getClassLoader());
        WebResourceRoot resources = new StandardRoot(context);

        // 将baseDir里的东西安装到某个目录里面，必须是在/WEB-INF/classes下面
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                "D:/develop/workspace/study/learn-servlet-and-tomcat/servlet-basics-embed-tomcat/target/classes", "/sbet"));

        // 将baseDir里的东西安装到某个目录里面，必须是在/WEB-INF/classes下面
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/lib",
                "D:\\develop\\workspace\\study\\learn-servlet-and-tomcat\\simple-servlet-jar", "/target"));


        context.setResources(resources);

        tomcat.start();
        tomcat.getServer().await();
    }

    private static String createTomcatBaseDir() {
        String baseDir = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        File file = new File(baseDir.replaceAll("target/classes", "target") + "tomcat");
        System.out.println("tomcatBaseDir: " + file.getAbsolutePath());
        if(!file.exists()) {
            file.mkdir();
        }
        return file.toString();
    }

}
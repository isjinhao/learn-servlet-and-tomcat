package hc;

import java.io.File;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

/**
 * @Author ISJINHAO
 * @Date 2021/1/8 9:39
 */
public class TomcatBootStrapWithHttps {

    public static void main(String[] args) throws LifecycleException {

        // 获取类加载器的基本路径
        String baseDir = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println("baseDir  : " + baseDir);

        // 启动 tomcat
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(createTomcatBaseDir());

        tomcat.setAddDefaultWebXmlToWebapp(false);

        tomcat.setPort(Integer.getInteger("port", 8080));
        tomcat.getConnector();

        // 创建 WebApp，contextPath就是url路径前缀
        Context context = tomcat.addWebapp("", baseDir);

        context.setParentClassLoader(TomcatBootStrapWithHttps.class.getClassLoader());
        WebResourceRoot resources = new StandardRoot(context);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                "D:/develop/workspace/study/learn-servlet-and-tomcat/servlet-basics-embed-tomcat/target/classes", "/sbet"));

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
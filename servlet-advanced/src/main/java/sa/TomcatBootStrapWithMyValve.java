package sa;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Pipeline;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.tomcat.util.modeler.Registry;
import sa.valve.MyValve;

import java.io.File;

/**
 * @Author ISJINHAO
 * @Date 2021/1/8 9:39
 */
public class TomcatBootStrapWithMyValve {

    public static void main(String[] args) throws LifecycleException {

        Registry.disableRegistry();

        // 获取类加载器的基本路径
        String baseDir = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println("baseDir  : " + baseDir);

        // 启动 tomcat
        Tomcat tomcat = new Tomcat();

        tomcat.setBaseDir(TomcatBootStrapWithMyValve.createTomcatBaseDir());

        tomcat.setPort(Integer.getInteger("port", 8080));
        tomcat.getConnector();

        // 创建 WebApp，contextPath就是url路径前缀
        Context context = tomcat.addWebapp("", baseDir);
        context.setParentClassLoader(TomcatBootStrapWithMyValve.class.getClassLoader());
        WebResourceRoot resources = new StandardRoot(context);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", baseDir, "/"));
        context.setResources(resources);


        // 按照 Tomcat 的设计，一个Engine下面可以有很多Host，但是在我们的程序里面只存在一个Host，
        // 所以在Host和Engine里面加入拦截都是正确的

//        Pipeline pipeline = tomcat.getHost().getPipeline();

        Pipeline pipeline = tomcat.getEngine().getPipeline();
        pipeline.addValve(new MyValve());

        tomcat.start();
        tomcat.getServer().await();
    }

    private static String createTomcatBaseDir() {
        String baseDir = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        File file = new File(baseDir.replaceAll("target/classes", "target") + "tomcat");
        if(!file.exists()) {
            file.mkdir();
        }
        return file.toString();
    }

}
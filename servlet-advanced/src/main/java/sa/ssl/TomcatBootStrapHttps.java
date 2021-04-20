package sa.ssl;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.apache.tomcat.util.modeler.Registry;

import java.io.File;

/**
 * @Author ISJINHAO
 * @Date 2021/2/4 16:51
 */
public class TomcatBootStrapHttps {

    public static void main(String[] args) throws LifecycleException {

        Registry.disableRegistry();
        Tomcat tomcat = new Tomcat();

        /**
         * 配置Connector
         */
        Connector connector = createConnector();
        tomcat.getService().addConnector(connector);

        /**
         * 默认的Servlet有两个，一个是 DefaultServlet，一个是 JspServlet
         * 不要加入默认的 Servlet，因为默认的 Servlet 会包含JSP相关的 Servlet，我们不需要这些。
         */
        tomcat.setAddDefaultWebXmlToWebapp(false);

        /**
         * 给Tomcat设置一个BaseDir，可以在这里面配置端口号等信息，实际上这个文件夹没有作用，
         * 如果我们不配置，
         */
        tomcat.setBaseDir(createTomcatBaseDir());

        // 获取类加载器的基本路径
        String baseDir = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println("baseDir  : " + baseDir);

        // 创建 WebApp，contextPath就是url路径前缀
        Context context = tomcat.addWebapp("", "D:\\develop\\workspace\\java\\back-end\\learn-servlet-and-tomcat\\servlet-basics-embed-tomcat\\src\\main\\resources");
        context.setParentClassLoader(TomcatBootStrapHttps.class.getClassLoader());

        WebResourceRoot resources = new StandardRoot(context);
        // 将baseDir里的东西安装到某个目录里面，必须是在/WEB-INF/classes下面
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes/AAA", baseDir, "/"));
        context.setResources(resources);

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

    protected static Connector createConnector() {
        Connector connector = new Connector();
        connector.setThrowOnFailure(true);

        connector.setPort(8080);
        connector.setProperty("server", "my tomcat");
        connector.setURIEncoding("UTF-8");
        connector.setProperty("bindOnInit", "false");

        AbstractHttp11Protocol protocol = (AbstractHttp11Protocol)connector.getProtocolHandler();
        protocol.setMaxThreads(200);
        protocol.setMinSpareThreads(10);
        protocol.setMaxHttpHeaderSize(8192);
        protocol.setMaxSwallowSize(2097152);
        connector.setMaxPostSize(2097152);
        protocol.setMaxConnections(8192);
        protocol.setAcceptCount(100);
        protocol.setProcessorCache(200);

        return connector;
    }

}

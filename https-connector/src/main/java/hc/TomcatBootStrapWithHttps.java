package hc;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.coyote.http11.AbstractHttp11JsseProtocol;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;

import java.io.File;

/**
 * @Author ISJINHAO
 * @Date 2021/1/8 9:39
 */
public class TomcatBootStrapWithHttps {

    static String baseDir = TomcatBootStrapWithHttps.class.getClassLoader().getResource("").getPath();

    public static void main(String[] args) throws LifecycleException {
        // 获取类加载器的基本路径
        System.out.println("baseDir  : " + baseDir);

        // 启动 tomcat
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(createTomcatBaseDir());

        tomcat.setAddDefaultWebXmlToWebapp(false);

        tomcat.getService().addConnector(getHttpRedirectConnector());
        tomcat.getService().addConnector(getHttpsConnector());

        // 创建 WebApp，contextPath就是url路径前缀
        Context context = tomcat.addWebapp("", baseDir);

        context.setParentClassLoader(TomcatBootStrapWithHttps.class.getClassLoader());
        WebResourceRoot resources = new StandardRoot(context);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", baseDir, "/"));

        context.setResources(resources);

        configureContext(context);

        tomcat.start();
        tomcat.getServer().await();
    }

    private static void configureContext(Context context) {
        SecurityConstraint securityConstraint = new SecurityConstraint();
        securityConstraint.setUserConstraint("CONFIDENTIAL");
        SecurityCollection collection = new SecurityCollection();
        collection.addPattern("/*");
        securityConstraint.addCollection(collection);
        context.addConstraint(securityConstraint);
    }


    private static Connector getHttpRedirectConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(8080);
        connector.setSecure(false);
        connector.setRedirectPort(8848);
        return connector;
    }


    private static Connector getHttpsConnector() {
        Connector connector = new Connector();

        connector.setScheme("https");
        connector.setSecure(true);
        connector.setPort(8848);

        AbstractHttp11JsseProtocol<?> protocolHandler = (AbstractHttp11JsseProtocol<?>)connector.getProtocolHandler();
        protocolHandler.setSSLEnabled(true);
        protocolHandler.setSslProtocol("TLS");
        protocolHandler.setKeystorePass("serverstorepw");
        protocolHandler.setKeyPass("serverkeypw");
        protocolHandler.setKeystoreFile(baseDir + "/server.private");
        protocolHandler.setKeystoreType("JKS");

        return connector;
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
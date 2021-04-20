package tisb;

import org.apache.catalina.Engine;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.apache.tomcat.util.modeler.Registry;

import java.io.File;

/**
 * @Author ISJINHAO
 * @Date 2021/2/4 16:51
 */
public class SpringBootStartTomcat {

    public static void main(String[] args) {

        Registry.disableRegistry();
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(createTomcatBaseDir());

        // Http 1.1
        Connector connector = new Connector();
        connector.setThrowOnFailure(true);
        tomcat.getService().addConnector(connector);
        customizeConnector(connector);
        tomcat.setConnector(connector);
        tomcat.getHost().setAutoDeploy(false);
        configureEngine(tomcat.getEngine());
//        prepareContext(tomcat.getHost(), initializers);

    }

    private static String createTomcatBaseDir() {
        String baseDir = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        File file = new File(baseDir.replaceAll("target/classes", "target") + "tomcat");
        if(!file.exists()) {
            file.mkdir();
        }
        return file.toString();
    }

    protected static void customizeConnector(Connector connector) {
        connector.setPort(8080);
        connector.setProperty("server", "模仿springboot启动的tomcat");
//        invokeProtocolHandlerCustomizers(connector.getProtocolHandler());
            connector.setURIEncoding("UTF-8");
        // Don't bind to the socket prematurely if ApplicationContext is slow to start
        connector.setProperty("bindOnInit", "false");

        // 压缩和SSL后面说
//        TomcatConnectorCustomizer compression = new CompressionConnectorCustomizer(getCompression());
//        compression.customize(connector);


        AbstractHttp11Protocol protocol = (AbstractHttp11Protocol)connector.getProtocolHandler();
        protocol.setMaxThreads(200);
        protocol.setMinSpareThreads(10);
        protocol.setMaxHttpHeaderSize(8192);
        protocol.setMaxSwallowSize(2097152);
        connector.setMaxPostSize(2097152);
        protocol.setMaxConnections(8192);
        protocol.setAcceptCount(100);
        protocol.setProcessorCache(200);

        //
//        for (TomcatConnectorCustomizer customizer : this.tomcatConnectorCustomizers) {
//            customizer.customize(connector);
//        }
    }

    private static void configureEngine(Engine engine) {
        engine.setBackgroundProcessorDelay(10);
    }

//    protected static void prepareContext(Host host, ServletContextInitializer[] initializers) {
//        File documentRoot = getValidDocumentRoot();
//        TomcatEmbeddedContext context = new TomcatEmbeddedContext();
//        if (documentRoot != null) {
//            context.setResources(new TomcatServletWebServerFactory.LoaderHidingResourceRoot(context));
//        }
//        context.setName(getContextPath());
//        context.setDisplayName(getDisplayName());
//        context.setPath(getContextPath());
//        File docBase = (documentRoot != null) ? documentRoot : createTempDir("tomcat-docbase");
//        context.setDocBase(docBase.getAbsolutePath());
//        context.addLifecycleListener(new Tomcat.FixContextListener());
//        context.setParentClassLoader((this.resourceLoader != null) ? this.resourceLoader.getClassLoader()
//                : ClassUtils.getDefaultClassLoader());
//        resetDefaultLocaleMapping(context);
//        addLocaleMappings(context);
//        try {
//            context.setCreateUploadTargets(true);
//        }
//        catch (NoSuchMethodError ex) {
//            // Tomcat is < 8.5.39. Continue.
//        }
//        configureTldPatterns(context);
//        WebappLoader loader = new WebappLoader();
//        loader.setLoaderClass(TomcatEmbeddedWebappClassLoader.class.getName());
//        loader.setDelegate(true);
//        context.setLoader(loader);
//        if (isRegisterDefaultServlet()) {
//            addDefaultServlet(context);
//        }
//        if (shouldRegisterJspServlet()) {
//            addJspServlet(context);
//            addJasperInitializer(context);
//        }
//        context.addLifecycleListener(new TomcatServletWebServerFactory.StaticResourceConfigurer(context));
//        ServletContextInitializer[] initializersToUse = mergeInitializers(initializers);
//        host.addChild(context);
//        configureContext(context, initializersToUse);
//        postProcessContext(context);
//    }

}

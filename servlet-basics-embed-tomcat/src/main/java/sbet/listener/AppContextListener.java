package sbet.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author ISJINHAO
 * @Date 2021/1/12 18:25
 */
@WebListener
public class AppContextListener implements ServletContextListener {

    public AppContextListener() {
        System.out.println("ServletContextListener's constructor is called!");
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        System.out.println("ServletContextListener  -->  AppContextListener Initialize");
        // create the thread pool
        ThreadPoolExecutor executorService =
                new ThreadPoolExecutor(
                        2,
                        3,
                        50000L,
                        TimeUnit.MILLISECONDS,
                        new ArrayBlockingQueue<>(100));
        servletContextEvent.getServletContext().setAttribute("executorService", executorService);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContextListener  -->  AppContextListener destroy");
        ThreadPoolExecutor executor = (ThreadPoolExecutor) servletContextEvent
                .getServletContext().getAttribute("executorService");
        executor.shutdown();
    }
}
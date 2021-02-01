package sa.initializer;

import javax.servlet.ServletContext;

/**
 * @Author ISJINHAO
 * @Date 2021/1/9 19:29
 */
public interface InitializerHandled {

    /**
     * Servlet容器启动时执行的方法
     * @param servletContext
     */
    void onStartUp(ServletContext servletContext);

}

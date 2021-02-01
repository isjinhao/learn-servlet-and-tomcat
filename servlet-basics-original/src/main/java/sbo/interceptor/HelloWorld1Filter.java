package sbo.interceptor;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Author ISJINHAO
 * @Date 2021/1/9 15:06
 */
@WebFilter(filterName = "helloWorld1Filter", servletNames = "helloWorldServlet")
public class HelloWorld1Filter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("helloWorld1Filter is called");
        System.out.println("helloWorld1Filter invoke");
        chain.doFilter(request, response);
        System.out.println("helloWorld1Filter is finished");
    }

    @Override
    public void destroy() {

    }
}

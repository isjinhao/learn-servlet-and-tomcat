package sbo.interceptor;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author ISJINHAO
 * @Date 2021/1/9 15:06
 */
public class HelloWorld3Filter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("helloWorld3Filter is called");
        System.out.println("helloWorld3Filter invoke");
        chain.doFilter(request, response);
        System.out.println("helloWorld3Filter is finished");
    }

    @Override
    public void destroy() {

    }

}

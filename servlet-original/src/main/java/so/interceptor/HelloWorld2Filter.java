package so.interceptor;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Author ISJINHAO
 * @Date 2021/1/9 15:06
 */
@WebFilter(filterName = "helloWorld2Filter", servletNames = "helloWorldServlet")
public class HelloWorld2Filter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("helloWorld2Filter is called");
        System.out.println("helloWorld2Filter invoke");
        chain.doFilter(request, response);
        System.out.println("helloWorld2Filter is finished");
    }

    @Override
    public void destroy() {

    }

}

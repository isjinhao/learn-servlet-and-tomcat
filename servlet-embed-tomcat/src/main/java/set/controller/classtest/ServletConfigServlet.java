package set.controller.classtest;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @Author ISJINHAO
 * @Date 2021/1/12 10:46
 */
@WebServlet(name = "servletConfig", urlPatterns = "/test/servletConfig",
    initParams = {@WebInitParam(name = "annotation-init-param", value = "wca")})
public class ServletConfigServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletConfig servletConfig = getServletConfig();
        System.out.println(servletConfig.getServletName());
        Enumeration<String> initParameterNames = servletConfig.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String name = initParameterNames.nextElement();
            System.out.println(name + " " + servletConfig.getInitParameter(name));
        }

    }
}

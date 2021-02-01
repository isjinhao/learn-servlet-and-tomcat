package sa.controller.classtest;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author ISJINHAO
 * @Date 2021/1/12 13:54
 */
@WebServlet(urlPatterns = "/test/sessionCookieConfig")
public class SessionCookieConfigServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = req.getServletContext();
        SessionCookieConfig sessionCookieConfig = servletContext.getSessionCookieConfig();

        String name = sessionCookieConfig.getName();
        int maxAge = sessionCookieConfig.getMaxAge();
        String domain = sessionCookieConfig.getDomain();
        String path = sessionCookieConfig.getPath();
        String comment = sessionCookieConfig.getComment();
        System.out.println(name + " " + maxAge + " " + domain + " " + path + " " + comment);

    }

}

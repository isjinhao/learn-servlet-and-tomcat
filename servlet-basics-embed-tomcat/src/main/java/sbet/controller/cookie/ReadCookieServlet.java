package sbet.controller.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author ISJINHAO
 * @Date 2021/1/12 14:22
 */
@WebServlet(urlPatterns = "/cookie/read")
public class ReadCookieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie[] cookies = req.getCookies();
        if(cookies == null) {
            return;
        }
        for (Cookie cookie : cookies) {
            System.out.println("从前端传来的Cookie：name=" + cookie.getName() + ",value=" + cookie.getValue());
        }

    }
}

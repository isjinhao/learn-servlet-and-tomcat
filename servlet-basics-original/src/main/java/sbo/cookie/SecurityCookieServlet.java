package sbo.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author ISJINHAO
 * @Date 2021/2/4 14:15
 */
@WebServlet(name = "securityCookieServlet", urlPatterns = {"/cookie/securityCookie"})
public class SecurityCookieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie cookie = new Cookie("SecurityCookie", "hengha");
        cookie.setSecure(true);
        cookie.setPath("/servlet_basics_original/cookie");
        cookie.setMaxAge(60 * 60 * 24);

        resp.addCookie(cookie);

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html;charset=UTF-8");
        out.println("write Cookie success！  ");
        out.flush();
        out.close();

    }

}

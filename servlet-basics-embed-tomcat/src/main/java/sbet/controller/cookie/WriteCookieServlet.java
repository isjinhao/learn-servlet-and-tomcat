package sbet.controller.cookie;

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
 * @Date 2021/1/12 14:08
 */
@WebServlet(urlPatterns = "/cookie/write")
public class WriteCookieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie cookie = new Cookie("testCookie", "aha");
        cookie.setPath("/servlet_basics_original/cookie");
        cookie.setDomain("localhost");
        resp.setContentType("application/json;charset=utf-8");
        // 1天
        cookie.setMaxAge(60 * 60 * 24);
        resp.addCookie(cookie);
        PrintWriter out = resp.getWriter();
        out.println("write Cookie success！");
        out.flush();
        out.close();

    }
}

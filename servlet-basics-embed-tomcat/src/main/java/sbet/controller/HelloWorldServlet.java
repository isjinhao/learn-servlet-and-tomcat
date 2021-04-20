package sbet.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author ISJINHAO
 * @Date 2021/1/7 18:35
 */
@WebServlet(
        name = "helloWorldServlet",
        urlPatterns = {"/helloWorld"}
)
public class HelloWorldServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        req.setAttribute("requestTest", "requestValue");
        req.removeAttribute("requestTest");

        HttpSession session = req.getSession();
        session.setAttribute("sessionTest", "sessionValue");
        session.removeAttribute("sessionTest");

        ServletContext servletContext = req.getServletContext();
        servletContext.setAttribute("contextTest", "contextValue");
        servletContext.removeAttribute("contextTest");

        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.write("hello world : servlet-original".getBytes());
        outputStream.flush();
        outputStream.close();

    }

    @Override
    public void init() throws ServletException {
        System.out.println("init HelloWorldServlet");
    }

}

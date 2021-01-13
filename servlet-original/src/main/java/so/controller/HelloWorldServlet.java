package so.controller;

import com.sun.xml.internal.ws.client.RequestContext;

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
        ServletOutputStream outputStream = resp.getOutputStream();

        req.setAttribute("requestTest", "requestValue");
        req.removeAttribute("");

        HttpSession session = req.getSession();

        ServletContext servletContext = req.getServletContext();



        outputStream.write("hello world : servlet-original".getBytes());
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init HelloWorldServlet");
    }

}

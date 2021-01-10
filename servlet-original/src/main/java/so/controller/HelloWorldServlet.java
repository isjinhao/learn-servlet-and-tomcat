package so.controller;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author ISJINHAO
 * @Date 2021/1/7 18:35
 */
public class HelloWorldServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        ServletOutputStream outputStream = resp.getOutputStream();

        outputStream.write("hello world : servlet-original".getBytes());

    }

    @Override
    public void init() throws ServletException {
        System.out.println("init");
        super.init();
    }

}

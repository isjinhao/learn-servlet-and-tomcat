package sa.controller;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author ISJINHAO
 * @Date 2021/1/11 15:09
 */
public class HelloChinaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.write("hello china : servlet-original".getBytes());
        outputStream.flush();
        outputStream.close();
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init HelloChinaServlet");
    }

}

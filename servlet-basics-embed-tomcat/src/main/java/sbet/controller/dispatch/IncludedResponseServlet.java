package sbet.controller.dispatch;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author ISJINHAO
 * @Date 2021/1/11 20:07
 */
@WebServlet("/dispatch/includedResponse")
public class IncludedResponseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 一个Request只能打开一次输入流，所以这里必须将Writer传过来
        PrintWriter writer = (PrintWriter) req.getAttribute("includeWriter");
        writer.println("includedResponse");
        writer.flush();

    }
}

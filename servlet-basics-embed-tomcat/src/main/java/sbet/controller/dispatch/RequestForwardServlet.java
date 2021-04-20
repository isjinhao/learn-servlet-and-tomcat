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
 * @Date 2021/1/11 20:09
 */
@WebServlet("/dispatch/requestForward")
public class RequestForwardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();
        req.setAttribute("forwardWriter", writer);

        writer.println("Some do one...");

        // 最终会调到方法 ServletResponse#resetBuffer，所以上面的那句话并不会被输出
        // 当 ServletRequest#getRequestDispatcher 执行完成的时候，会将Response的Suspended状态设置为true，即之后的输出都会被吞没
        req.getRequestDispatcher("/dispatch/forwardedResponse").forward(req, resp);

        writer.println("Some do two");
        writer.flush();
        writer.close();
    }

}

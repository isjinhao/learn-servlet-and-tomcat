package sbo.controller.dispatch;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author ISJINHAO
 * @Date 2021/1/11 20:09
 */
@WebServlet("/dispatch/requestForward")
public class RequestForwardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("dispatch", "RequestForwardServlet");
        req.getRequestDispatcher("/dispatch/finalResponse").forward(req, resp);

    }

}

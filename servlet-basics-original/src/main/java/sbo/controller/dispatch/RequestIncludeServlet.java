package sbo.controller.dispatch;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author ISJINHAO
 * @Date 2021/1/11 22:04
 */
@WebServlet("/dispatch/requestInclude")
public class RequestIncludeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/helloWorld").include(req, resp);
        PrintWriter writer = resp.getWriter();
        writer.write(" RequestIncludeServlet");
        writer.flush();
        writer.close();

    }

}

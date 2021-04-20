package sbet.controller.dispatch;

import javax.servlet.RequestDispatcher;
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

        PrintWriter out = resp.getWriter();
        req.setAttribute("includeWriter", out);

        out.println("Some do one...");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/dispatch/includedResponse");
        dispatcher.include(req, resp);
        out.println("Some do two...");

        out.flush();
        out.close();

    }

}

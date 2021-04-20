package sbo.dispatch;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @Author ISJINHAO
 * @Date 2021/1/11 20:08
 */
@WebServlet("/dispatch/redirect")
public class RedirectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("dispatch", "RedirectServlet");

        // 不以/为开头，则以当前目录为基准进行跳转，比如此Servlet的当前目录就是/dispatch
        // /dispatch/finalResponse在当前目录下，所以直接使用finalResponse即可
//        resp.sendRedirect("forwardedResponse");


        // 以/开头，则表示从当前Host为基准，所以需要加上Context路径
        resp.sendRedirect("/servlet_basics_original/dispatch/forwardedResponse");

    }

}

package set.controller.push;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.PushBuilder;
import java.io.IOException;
import java.util.Set;

/**
 * @author 01395265
 * @date 2021/1/13
 */
@WebServlet(urlPatterns = "/push/pushBuilder")
public class PushBuilderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PushBuilder pushBuilder = req.newPushBuilder();

        System.out.println(pushBuilder.getMethod());
        System.out.println(pushBuilder.getPath());
        System.out.println(pushBuilder.getQueryString());

        System.out.println(pushBuilder.getSessionId());

        Set<String> headerNames = pushBuilder.getHeaderNames();
        headerNames.forEach(System.out::println);

    }

}

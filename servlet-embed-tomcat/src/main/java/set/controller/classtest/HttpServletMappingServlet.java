package set.controller.classtest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author ISJINHAO
 * @Date 2021/1/11 20:22
 */
@WebServlet(urlPatterns = "/test/httpServletMapping")
public class HttpServletMappingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpServletMapping mapping = req.getHttpServletMapping();
        String mapName = mapping.getMappingMatch().name();
        String value = mapping.getMatchValue();
        String pattern = mapping.getPattern();
        String servletName = mapping.getServletName();
        System.out.println(mapName + " " + value + " " + pattern + " " + servletName);

    }
}

package sbet.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author ISJINHAO
 * @Date 2021/4/20 23:10
 */
@WebServlet(
        name = "helloEarthServlet",
        urlPatterns = {"/helloEarth"}
)
public class HelloEarthServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String name = req.getParameter("name");
        System.out.println(name);

    }


}

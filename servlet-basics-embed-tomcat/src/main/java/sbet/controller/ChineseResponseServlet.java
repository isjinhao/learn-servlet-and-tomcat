package sbet.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author ISJINHAO
 * @Date 2021/1/12 11:07
 */
@WebServlet(urlPatterns = "/chineseResponse")
public class ChineseResponseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 指定Json类型的数据
//        resp.setContentType("application/json;charset=utf-8");

        // 指定html类型的数据
        resp.setHeader("content-type","text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write("{\n" +
                "    \"name\": \"你好啊\"\n" +
                "}");

        writer.flush();
        writer.close();
    }

}

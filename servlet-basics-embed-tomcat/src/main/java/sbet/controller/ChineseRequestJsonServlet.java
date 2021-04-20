package sbet.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author ISJINHAO
 * @Date 2021/1/12 11:07
 */
@WebServlet(urlPatterns = "/chineseRequestJson")
public class ChineseRequestJsonServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 从输入流里面解码时采用utf-8就行解码
        BufferedReader br = new BufferedReader(new InputStreamReader(
                req.getInputStream(), "utf-8"));
        StringBuffer sb = new StringBuffer("");
        String temp;
        while ((temp = br.readLine()) != null) {
            sb.append(temp);
        }
        br.close();
        System.out.println(sb.toString());

    }
}

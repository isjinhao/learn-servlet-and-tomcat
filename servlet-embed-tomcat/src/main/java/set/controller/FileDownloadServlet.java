package set.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;

/**
 * @Author ISJINHAO
 * @Date 2021/1/11 18:08
 */
@WebServlet(name = "fileDownloadServlet", urlPatterns = "/fileDownload")
public class FileDownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        URL resource = this.getClass().getClassLoader().getResource("/");
        System.out.println(resource.getPath());

        // filepath
        String filePath = resource.getPath() + "/spring结构.png";
        File file = new File(filePath);

        try (
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
                BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
        ) {
            long fileLength = new File(filePath).length();
            resp.setHeader("Content-disposition", "attachment; filename=" + new String(file.getName().getBytes("utf-8"), "ISO8859-1"));
            resp.setHeader("Content-Length", String.valueOf(fileLength));
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

package so.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author ISJINHAO
 * @Date 2021/1/11 18:09
 */
@WebServlet(name = "fileUploadServlet", urlPatterns = "/fileUpload")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 说明输入的请求信息采用UTF-8编码方式
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        // Servlet3.0中新引入的方法，用来处理multipart/form-data类型编码的表单
        Part part = req.getPart("file");

        // 获取HTTP头信息headerInfo=（form-data; name="file" filename="文件名"）
        String headerInfo = part.getHeader("content-disposition");

        System.out.println("headinfo  ==>  " + headerInfo);

        // 从HTTP头信息中获取文件名fileName=（文件名）
        String fileName = headerInfo.substring(headerInfo.lastIndexOf("=") + 2, headerInfo.length() - 1);

        // 获得存储上传文件的文件夹路径
        String fileSavedFolder = this.getServletContext().getRealPath("/upload");

        // 获得存储上传文件的完整路径（文件夹路径+文件名）
        // 文件夹位置固定，文件夹采用与上传文件的原始名字相同
        String fileSavingPath = fileSavedFolder + File.separator + fileName;
        System.out.println("filePath  ==>  " + fileSavingPath);
        // 如果存储上传文件的文件夹不存在，则创建文件夹
        File f = new File(fileSavedFolder + File.separator);
        if(!f.exists()){
            f.mkdirs();
        }
        // 将上传的文件内容写入服务器文件中
        part.write(fileSavingPath);
        // 输出上传成功信息
        out.println("文件上传成功~！");
    }
}

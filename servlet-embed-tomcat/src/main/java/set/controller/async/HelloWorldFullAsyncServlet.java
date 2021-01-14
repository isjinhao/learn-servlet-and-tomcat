package set.controller.async;

import set.listener.async.AppAsyncListener;
import set.listener.async.MyReadListener;
import set.listener.async.MyWriteListener;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

/**
 * @Author ISJINHAO
 * @Date 2021/1/12 15:06
 */
@WebServlet(urlPatterns = "/async/helloWorldFull", asyncSupported = true)
public class HelloWorldFullAsyncServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        System.out.println("Start ThreadName="
                + Thread.currentThread().getName() + ", ThreadId="
                + Thread.currentThread().getId());

        request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);

        AsyncContext asyncCtx = request.startAsync();

        // 异步servlet的超时时间,异步Servlet有对应的超时时间，如果在指定的时间内没有执行完操作
        // response依然会走原来Servlet的结束逻辑，后续的异步操作执行完再写回的时候，可能会遇到异常。
        asyncCtx.setTimeout(9000);

        asyncCtx.addListener(new AppAsyncListener());

        ExecutorService executorService = (ExecutorService) request
                .getServletContext().getAttribute("executorService");

        ServletInputStream inputStream = request.getInputStream();
        inputStream.setReadListener(new MyReadListener(asyncCtx, executorService));

        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.setWriteListener(new MyWriteListener(asyncCtx));

        long endTime = System.currentTimeMillis();
        System.out.println("Start ThreadName="
                + Thread.currentThread().getName() + ", ThreadId="
                + Thread.currentThread().getId() + ", "
                + (endTime - startTime) + " ms.");
    }

}

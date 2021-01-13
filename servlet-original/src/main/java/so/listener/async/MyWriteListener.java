package so.listener.async;

import javax.servlet.*;
import java.io.IOException;
import so.controller.async.HelloWorldAsyncServlet;

/**
 * @Author ISJINHAO
 * @Date 2021/1/9 19:40
 */
public class MyWriteListener implements WriteListener {

    private AsyncContext asyncContext;

    public MyWriteListener(AsyncContext asyncContext) {
        this.asyncContext = asyncContext;
    }

    @Override
    public void onWritePossible() throws IOException {
        ServletResponse response = asyncContext.getResponse();

        ServletOutputStream outputStream = response.getOutputStream();

        outputStream.write("HelloWorldFullAsyncServlet".getBytes());

        asyncContext.complete();
    }

    @Override
    public void onError(Throwable t) {
        System.out.println(t);
    }

}

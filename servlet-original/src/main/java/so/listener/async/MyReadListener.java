package so.listener.async;

import so.controller.async.HelloWorldAsyncServlet;

import javax.servlet.AsyncContext;
import javax.servlet.ReadListener;
import javax.servlet.ServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;

/**
 * @Author ISJINHAO
 * @Date 2021/1/9 19:39
 */
public class MyReadListener implements ReadListener {

    private AsyncContext asyncContext;
    private ExecutorService executorService;

    public MyReadListener(AsyncContext asyncContext, ExecutorService executorService) {
        this.asyncContext = asyncContext;
        this.executorService = executorService;
    }

    @Override
    public void onDataAvailable() throws IOException {
        // 在这里打一个断点，看看Tomcat对请求的处理是不是委托到真正调用的时候
    }

    @Override
    public void onAllDataRead() throws IOException {
        ServletRequest request = asyncContext.getRequest();
        executorService.execute(() -> {
            HelloWorldAsyncServlet.sleep();
            String name = request.getParameter("name");
            System.out.println(Thread.currentThread().getName() + " HelloWorldFullAsync  -->  " + name);
        });
    }

    @Override
    public void onError(Throwable t) {
        System.out.println(t);
    }

}

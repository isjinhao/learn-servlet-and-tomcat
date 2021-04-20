package sa.nio;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.AsyncContext;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;

public class MyReadListener implements ReadListener {

    private ServletInputStream inputStream;
    private AsyncContext asyncContext;

    public MyReadListener(ServletInputStream input, AsyncContext context) {
        this.inputStream = input;
        this.asyncContext = context;
    }

    // 数据可用时触发执行
    @Override
    public void onDataAvailable() throws IOException {
        System.out.println("数据可用时触发执行");
    }

    // 数据读完时触发调用
    @Override
    public void onAllDataRead() throws IOException {
        try {
            // 暂停5秒，模拟处理数据
            Thread.sleep(3000);
            PrintWriter out = asyncContext.getResponse().getWriter();
            out.write("数据读完了");
            out.flush();
            System.out.println("数据读完了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 数据出错触发调用
    @Override
    public void onError(Throwable t) {
        System.out.println("数据 出错");
        t.printStackTrace();
    }
}

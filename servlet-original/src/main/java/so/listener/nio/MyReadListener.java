package so.listener.nio;

import javax.servlet.ReadListener;
import java.io.IOException;

/**
 * @Author ISJINHAO
 * @Date 2021/1/9 19:39
 */
public class MyReadListener implements ReadListener {
    @Override
    public void onDataAvailable() throws IOException {

        // 在这里打一个断点，看看Tomcat对请求的处理是不是委托到真正调用的时候

    }

    @Override
    public void onAllDataRead() throws IOException {

    }

    @Override
    public void onError(Throwable t) {

    }
}

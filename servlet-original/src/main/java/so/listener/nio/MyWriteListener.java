package so.listener.nio;

import javax.servlet.WriteListener;
import java.io.IOException;

/**
 * @Author ISJINHAO
 * @Date 2021/1/9 19:40
 */
public class MyWriteListener implements WriteListener {
    @Override
    public void onWritePossible() throws IOException {




    }

    @Override
    public void onError(Throwable t) {

    }
}

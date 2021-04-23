package sa;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(value = "/async-write", asyncSupported = true)
public class AsyncWriteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Servlet thread: " + Thread.currentThread().getName());
        AsyncContext asyncCtx = req.startAsync();
        ServletOutputStream os = resp.getOutputStream();
        InputStream bigfileInputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("bigfile");

        os.setWriteListener(new WriteListener() {

            @Override
            public void onWritePossible() throws IOException {
                int loopCount = 0;
                System.out.println("WriteListener thread: " + Thread.currentThread().getName());
                while (os.isReady()) {
                    loopCount++;
                    System.out.println("Loop Count: " + loopCount);
                    byte[] bytes = readContent();
                    if (bytes != null) {
                        os.write(bytes);
                    } else {
                        closeInputStream();
                        asyncCtx.complete();
                        break;
                    }
                }
            }

            @Override
            public void onError(Throwable t) {
                try {
                    os.print("Error happened");
                    os.print(t.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeInputStream();
                    asyncCtx.complete();
                }
            }

            private byte[] readContent() throws IOException {
                byte[] bytes = new byte[1024];
                int readLength = IOUtils.read(bigfileInputStream, bytes, 0, bytes.length);
                if (readLength <= 0) {
                    return null;
                }
                return bytes;
            }

            private void closeInputStream() {
                IOUtils.closeQuietly(bigfileInputStream);
            }
        });

    }

}
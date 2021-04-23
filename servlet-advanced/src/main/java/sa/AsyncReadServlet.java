package sa;

import sa.listener.AppAsyncListener;

import javax.servlet.AsyncContext;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/async-read", asyncSupported = true)
public class AsyncReadServlet extends HttpServlet {

    // curl.exe -X POST -T D:\apache-maven-3.6.3-bin.zip --limit-rate 5k http://localhost:8080/async-read
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Servlet thread: " + Thread.currentThread().getName());
        AsyncContext asyncCtx = req.startAsync();

        asyncCtx.addListener(new AppAsyncListener());

        ServletInputStream is = req.getInputStream();
        is.setReadListener(new ReadListener() {
            private int totalReadBytes = 0;

            @Override
            public void onDataAvailable() {
                try {
                    byte buffer[] = new byte[1 * 65536];
                    int readBytes = 0;
                    while (is.isReady() && !is.isFinished()) {
                        int length = is.read(buffer);
                        System.out.println("current fragment : " + length);
                        if (length == -1 && is.isFinished()) {
                            asyncCtx.complete();
                            System.out.println("Read: " + readBytes + " bytes");
                            System.out.println("Total Read: " + totalReadBytes + " bytes");
                            return;
                        }
                        readBytes += length;
                        totalReadBytes += length;

                    }
                    System.out.println("ReadListener thread: " + Thread.currentThread().getName() + ", Read: " + readBytes + " bytes");

                } catch (IOException ex) {
                    ex.printStackTrace();
                    asyncCtx.complete();
                }
            }

            @Override
            public void onAllDataRead() {
                try {
                    System.out.println("Total Read: " + totalReadBytes + " bytes");
                    asyncCtx.getResponse().getWriter().println("Finished");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                asyncCtx.complete();
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
                asyncCtx.complete();
            }
        });

    }

}
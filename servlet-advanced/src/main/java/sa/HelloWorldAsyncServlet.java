package sa;

import sa.listener.AppAsyncListener;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author ISJINHAO
 * @Description
 * @Date 2021/1/11 17:19
 */
@WebServlet(
        name = "helloWorldAsyncServlet",
        urlPatterns = {"/async/helloWorld"},
        asyncSupported = true,
        loadOnStartup = 1
)
public class HelloWorldAsyncServlet extends HttpServlet {

    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        AsyncContext asyncContext = req.startAsync();

        asyncContext.addListener(new AppAsyncListener());

        System.out.println("async helloworld servlet is called");
        executorService.execute(() -> {
            HelloWorldAsyncServlet.sleep();
            try {
                String name = asyncContext.getRequest().getParameter("name");
                System.out.println(name);
                System.out.println("async helloworld servlet output data is called");
                asyncContext.getResponse().getWriter().write("HelloWorldAsyncServlet");
                System.out.println("async helloworld servlet output data finished");
            } catch (IOException e) {
                e.printStackTrace();
            }
            asyncContext.complete();
        });
        System.out.println("async helloworld servlet finished");

    }

    public static void sleep() {
        try {
            int millis = ThreadLocalRandom.current().nextInt(1000, 2000);
            String currentThread = Thread.currentThread().getName();
            System.out.println(currentThread + " sleep for " + millis + " milliseconds.");
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init HelloWorldAsyncServlet");
    }

}

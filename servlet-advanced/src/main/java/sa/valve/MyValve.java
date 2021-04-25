package sa.valve;

import org.apache.catalina.Valve;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @Author ISJINHAO
 * @Date 2021/4/25 16:49
 */
public class MyValve implements Valve {

    private Valve next;

    @Override
    public Valve getNext() {
        return next;
    }

    @Override
    public void setNext(Valve valve) {
        next = valve;
    }

    @Override
    public void backgroundProcess() {

    }

    @Override
    public void invoke(Request request, Response response) throws IOException, ServletException {

        System.out.println("=====================start===================");

        System.out.println("#getNext().getClass().getName(): " + getNext().getClass().getName());
        System.out.println(this.getClass().getName() + "#invoke");

        System.out.println("request: " + request);
        System.out.println("response: " + response);

        System.out.println("request.getServletPath():" + request.getServletPath());

        System.out.println("request.getQueryString():" + request.getQueryString());

        //例如这里可以获取请求体长度，用来记录请求流量
        System.out.println("request.getContentLength(): " + request.getContentLength());

        //例如获取响应的流量
        System.out.println("response.getBytesWritten(false): " + response.getBytesWritten(false));

        System.out.println("==================end======================");

        getNext().invoke(request, response);
    }

    @Override
    public boolean isAsyncSupported() {
        return true;
    }
}
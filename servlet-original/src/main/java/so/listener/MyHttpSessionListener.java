package so.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @Author ISJINHAO
 * @Date 2021/1/9 18:56
 */
@WebListener
public class MyHttpSessionListener implements HttpSessionListener {

    public MyHttpSessionListener() {
        System.out.println("HttpSessionListener's constructor is called!");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("HttpSessionListener  -->  session created : " + se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("HttpSessionListener  -->  session destroyed : " + se.getSession());
    }

}

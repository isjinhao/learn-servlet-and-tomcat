package sa.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * @Author ISJINHAO
 * @Date 2021/1/9 18:55
 */
@WebListener
public class MyServletRequestListener implements ServletRequestListener {

    public MyServletRequestListener() {
        System.out.println("ServletRequestListener's constructor is called!");

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("ServletRequestListener  -->  request initialized : " + sre.getServletRequest());
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("ServletRequestListener  -->  request destroyed : " + sre.getServletContext());
    }

}

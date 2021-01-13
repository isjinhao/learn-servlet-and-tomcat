package so.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * @Author ISJINHAO
 * @Date 2021/1/9 18:56
 */
@WebListener
public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        String name = event.getName();
        Object attribute = event.getSession().getAttribute(name);
        System.out.println("add session attribute, attribute name: " + name + " , attribute value: " + attribute);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        String name = event.getName();
        System.out.println("remove session attribute, attribute name: " + name);
    }

}

package so.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * @Author ISJINHAO
 * @Date 2021/1/9 18:55
 */
@WebListener
public class MyServletContextAttributeListener implements ServletContextAttributeListener {

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        String name = event.getName();
        Object attribute = event.getServletContext().getAttribute(name);
        System.out.println("add context attribute, attribute name: " + name + " , attribute value: " + attribute);
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        String name = event.getName();
        System.out.println("remove context attribute, attribute name: " + name);
    }
}

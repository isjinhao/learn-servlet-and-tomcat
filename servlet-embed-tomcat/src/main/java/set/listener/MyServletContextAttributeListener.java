package set.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * @Author ISJINHAO
 * @Date 2021/1/9 18:55
 */
@WebListener
public class MyServletContextAttributeListener implements ServletContextAttributeListener {

    public MyServletContextAttributeListener() {
        System.out.println("ServletContextAttributeListener's constructor is called!");
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        String name = event.getName();
        Object attribute = event.getServletContext().getAttribute(name);
        System.out.println("ServletContextAttributeListener  -->  add context attribute, attribute name: " + name + " , attribute value: " + attribute);
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        String name = event.getName();
        System.out.println("ServletContextAttributeListener  -->  remove context attribute, attribute name: " + name);
    }
}

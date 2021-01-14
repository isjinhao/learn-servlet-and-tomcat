package set.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * @Author ISJINHAO
 * @Date 2021/1/9 18:55
 */
@WebListener
public class MyServletRequestAttributeListener implements ServletRequestAttributeListener {

    public MyServletRequestAttributeListener() {
        System.out.println("ServletRequestAttributeListener's constructor is called!");
    }

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        String name = srae.getName();
        Object attribute = srae.getServletRequest().getAttribute(name);
        System.out.println("ServletRequestAttributeListener  -->  add request attribute, attribute name: " + name + " , attribute value: " + attribute);
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        String name = srae.getName();
        System.out.println("ServletRequestAttributeListener  -->  remove request attribute, attribute name: " + name);
    }

}

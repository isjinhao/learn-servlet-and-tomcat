package sbo.initializer;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;

/**
 * @Author ISJINHAO
 * @Date 2021/1/9 19:27
 */
@HandlesTypes(InitializerHandled.class)
public class MyServletContainerInitializer implements ServletContainerInitializer {

    public MyServletContainerInitializer() {
        System.out.println("constructor of ServletContainerInitializer is called! ");
    }

    @Override
    public void onStartup(Set<Class<?>> initializerHandledClasses, ServletContext ctx) throws ServletException {

        System.out.println("onStartup of ServletContainerInitializer is called! ");

        try {
            for (Class<?> classItem : initializerHandledClasses) {
                InitializerHandled initializerHandled = (InitializerHandled) classItem.newInstance();
                initializerHandled.onStartUp(ctx);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

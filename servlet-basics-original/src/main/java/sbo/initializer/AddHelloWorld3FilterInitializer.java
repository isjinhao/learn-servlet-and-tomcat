package sbo.initializer;

import sbo.interceptor.HelloWorld3Filter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import java.util.EnumSet;

/**
 * @Author ISJINHAO
 * @Date 2021/1/11 15:22
 */
public class AddHelloWorld3FilterInitializer implements InitializerHandled {

    @Override
    public void onStartUp(ServletContext servletContext) {

        System.out.println("begin to add helloworld3filter to context!");

        FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("helloWorld3Filter", new HelloWorld3Filter());
        filterRegistration.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST), false, "helloWorldServlet");

        System.out.println("end to add helloworld3filter to context!");

    }

}

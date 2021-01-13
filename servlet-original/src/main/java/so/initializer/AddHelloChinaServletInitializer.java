package so.initializer;

import so.controller.HelloChinaServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * @Author ISJINHAO
 * @Date 2021/1/11 15:22
 */
public class AddHelloChinaServletInitializer implements InitializerHandled {

    @Override
    public void onStartUp(ServletContext servletContext) {

        System.out.println("begin to add hellochina to context!");

        ServletRegistration.Dynamic registration = servletContext.addServlet("helloChinaServlet", new HelloChinaServlet());
        registration.addMapping("/helloChina");

        System.out.println("end to add hellochina to context!");

    }

}

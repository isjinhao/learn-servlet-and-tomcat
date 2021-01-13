package so.controller.classtest;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author ISJINHAO
 * @Date 2021/1/12 9:42
 */
@WebServlet(urlPatterns = "/test/servletSecurity")
@ServletSecurity(
        value = @HttpConstraint(
                rolesAllowed = {
                        "aha-user", "hengha-user"
                },
                value = ServletSecurity.EmptyRoleSemantic.PERMIT,
                transportGuarantee = ServletSecurity.TransportGuarantee.NONE
        ),
        httpMethodConstraints = {
                @HttpMethodConstraint(
                        value = "GET",
                        rolesAllowed = {
                                "aha-user"
                        },
                        transportGuarantee = ServletSecurity.TransportGuarantee.NONE,
                        emptyRoleSemantic = ServletSecurity.EmptyRoleSemantic.PERMIT
                )
        }
)
public class ServletSecurityServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get method invoke");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("post method invoke");
    }
}

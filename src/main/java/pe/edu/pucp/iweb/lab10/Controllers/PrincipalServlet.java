package pe.edu.pucp.iweb.lab10.Controllers;

import pe.edu.pucp.iweb.lab10.Beans.Employee;
import pe.edu.pucp.iweb.lab10.Daos.PrincipalDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "PrincipalServlet", value = "/PrincipalServlet")
public class PrincipalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email") != null ? request.getParameter("email"): "";
        String password=request.getParameter("contrasena") != null ? request.getParameter("contrasena") : "";
        System.out.println(email);
        System.out.println(password);
        HttpSession session = request.getSession();
        if (!email.equalsIgnoreCase("") && !email.equalsIgnoreCase("")){
            PrincipalDao principalDao=new PrincipalDao();
            try {
                Employee employee = principalDao.validar(email,password);
                if(employee != null){
                    session.setAttribute("employee", employee);
                    response.sendRedirect(request.getContextPath()+"/EmployeeServlet");
                }else{
                    response.sendRedirect(request.getContextPath());
                }
            } catch (SQLException e) {
                response.sendRedirect(request.getContextPath());
            }
        }else{
            response.sendRedirect(request.getContextPath());
        }
    }
}

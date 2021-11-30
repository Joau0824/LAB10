package pe.edu.pucp.iweb.lab10.Controllers;

import pe.edu.pucp.iweb.lab10.Daos.DepartamentosDao;
import pe.edu.pucp.iweb.lab10.Daos.EmployeeDao;
import pe.edu.pucp.iweb.lab10.Daos.JobDao;
import pe.edu.pucp.iweb.lab10.Daos.PaisDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EmployeeServlet", value = "/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") != null ? request.getParameter("action") : "listar";
        HttpSession session = request.getSession();
        String rol = (String) session.getAttribute("rol");
        EmployeeDao employeeDao = new EmployeeDao();
        JobDao jobDao = new JobDao();
        PaisDao paisDao =  new PaisDao();
        DepartamentosDao departamentosDao = new DepartamentosDao();
        RequestDispatcher view;
        System.out.println("Rol es: "+rol);
        switch (action){
            case "listar":
                if(rol.equalsIgnoreCase("Top 1")){
                    System.out.println("Coloco el rol");
                    session.setAttribute("listaEmpleados",employeeDao.listarEmpleados());
                }else if(rol.equalsIgnoreCase("Top 2")){
                    session.setAttribute("listaTrabajos",jobDao.listarTrabajos());
                }else if(rol.equalsIgnoreCase("Top 3")){
                    session.setAttribute("listaDepartamentos",departamentosDao.listaDepartamentos());
                }else if(rol.equalsIgnoreCase("Top 4")){
                    session.setAttribute("listaPaises",paisDao.listar());
                }
                System.out.println("Voy a entrar a la vista");
                view = request.getRequestDispatcher("/Employee.jsp");
                view.forward(request, response);
                break;
            case "borrar":
                break;
            case "editar":
                break;
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

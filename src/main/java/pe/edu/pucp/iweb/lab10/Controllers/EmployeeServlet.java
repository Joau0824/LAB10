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
                if(rol.equalsIgnoreCase("T1")){
                    request.setAttribute("listaEmpleados",employeeDao.listarEmpleados());
                    view = request.getRequestDispatcher("/ListaEmpleados.jsp");
                    view.forward(request, response);
                }else if(rol.equalsIgnoreCase("T2")){
                    request.setAttribute("listaTrabajos",jobDao.listarTrabajos());
                    view = request.getRequestDispatcher("/ListaTrabajos.jsp");
                    view.forward(request, response);
                }else if(rol.equalsIgnoreCase("T3")){
                    request.setAttribute("listaDepartamentos",departamentosDao.listaDepartamentos());
                    view = request.getRequestDispatcher("/ListaDepartamentos.jsp");
                    view.forward(request, response);
                }else if(rol.equalsIgnoreCase("T4")){
                    request.setAttribute("listaPaises",paisDao.listar());
                    view = request.getRequestDispatcher("/ListaPaises.jsp");
                    view.forward(request, response);
                }
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

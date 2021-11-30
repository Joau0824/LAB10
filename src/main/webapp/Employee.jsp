<%@ page import="pe.edu.pucp.iweb.lab10.Beans.Employee" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pe.edu.pucp.iweb.lab10.Beans.Job" %>
<%@ page import="pe.edu.pucp.iweb.lab10.Beans.Department" %>
<%@ page import="pe.edu.pucp.iweb.lab10.Daos.PaisDao" %>
<%@ page import="pe.edu.pucp.iweb.lab10.Beans.Country" %>
<%String rol = session.getAttribute("rol") != null ? (String) session.getAttribute("rol") : "";%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%if(rol.equalsIgnoreCase("Top 1")){%>
<%ArrayList<Employee> employeeList = (ArrayList) session.getAttribute("listaEmpleados");%>
<!DOCTYPE html>
<html>
<head>
    <title>Lista empleados</title>
    <jsp:include page="includes/headCss.jsp"/>
</head>
<body>
<div class='container'>
    <jsp:include page="includes/navbar.jsp">
        <jsp:param name="currentPage" value="emp"/>
    </jsp:include>
    <div class="row mb-5 mt-4">
        <div class="col-md-7">
            <h1>Lista de empleados</h1>
        </div>
        <%
            Employee employeeSession = (Employee) session.getAttribute("employee");
            if (employeeSession.getJob().getJobId().equals("AD_PRES")) {
        %>
        <div class="col-md-5 col-lg-4 ms-auto my-auto text-md-end">
            <a href="<%= request.getContextPath()%>/EmployeeServlet?action=agregar" class="btn btn-primary">
                Agregar nuevo empleado</a>
        </div>
        <% } %>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th>#</th>
            <th>Employee</th>
            <th>Email</th>
            <th>Job ID</th>
            <th>Salary</th>
            <th>Commision</th>
            <th>Manager ID</th>
            <th>Department ID</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <%
            int i = 1;
            for (Employee e : employeeList) {
        %>
        <tr>
            <td><%= i%>
            </td>
            <td><%= e.getFirstName() + " " + e.getLastName()%>
            </td>
            <td><%= e.getEmail()%>
            </td>
            <td><%= e.getJob().getJobTitle()%>
            </td>
            <td><%= e.getSalary()%>
            </td>
            <td><%= e.getCommissionPct() == null ? "Sin comisión" : e.getCommissionPct()%>
            </td>
            <td><%= e.getManager().getEmployeeId() == 0 ? "Sin jefe" : (e.getManager().getFirstName() + " " + e.getManager().getLastName())%>
            </td>
            <td><%= e.getDepartment().getDepartmentName()%>
            </td>
            <td>
                <a href="<%=request.getContextPath()%>/EmployeeServlet?action=editar&id=<%= e.getEmployeeId()%>"
                   type="button" class="btn btn-primary">
                    <i class="bi bi-pencil-square"></i>
                </a>
            </td>
            <td>
                <a onclick="return confirm('¿Estas seguro de borrar?');"
                   href="<%=request.getContextPath()%>/EmployeeServlet?action=editar&id=<%= e.getEmployeeId()%>"
                   type="button" class="btn btn-danger">
                    <i class="bi bi-trash"></i>
                </a>
            </td>
        </tr>
        <%
                i++;
            }
        %>
        </tbody>
    </table>
    <jsp:include page="includes/footer.jsp"/>
</div>
</body>
</html>
<%}%>
<%if(rol.equalsIgnoreCase("Top 2")){%>
<%ArrayList<Job> listaTrabajos =(ArrayList) session.getAttribute("listaTrabajos");%>
<!DOCTYPE html>
<html>
<head>
    <title>Lista Trabajos</title>
    <jsp:include page="includes/headCss.jsp"/>
</head>
<body>
<div class='container'>
    <jsp:include page="/includes/navbar.jsp">
        <jsp:param name="currentPage" value="job"/>
    </jsp:include>
    <div class="row mb-5 mt-4">
        <div class="col-md-7">
            <h1 class=''>Lista de trabajos en hr</h1>
        </div>
        <div class="col-md-5 col-lg-4 ms-auto my-auto text-md-end">
            <a href="<%= request.getContextPath()%>/JobServlet?action=formCrear" class="btn btn-primary">Crear
                Trabajo</a>
        </div>
    </div>
    <% if (request.getParameter("msg") != null) {%>
    <div class="alert alert-success" role="alert"><%=request.getParameter("msg")%>
    </div>
    <% } %>
    <% if (request.getParameter("err") != null) {%>
    <div class="alert alert-danger" role="alert"><%=request.getParameter("err")%>
    </div>
    <% } %>
    <table class="table">
        <tr>
            <th>#</th>
            <th>Job ID</th>
            <th>Job Name</th>
            <th>Min Salary</th>
            <th>Max Salary</th>
            <th></th>
            <th></th>
        </tr>
        <%
            int i = 1;
            for (Job job : listaTrabajos) {
        %>
        <tr>
            <td><%=i%>
            </td>
            <td><%=job.getJobId()%>
            </td>
            <td><%=job.getJobTitle()%>
            </td>
            <td><%=job.getMinSalary()%>
            </td>
            <td><%=job.getMaxSalary()%>
            </td>
            <td>
                <a href="<%=request.getContextPath()%>/JobServlet?action=editar&id=<%=job.getJobId()%>">
                    Editar
                </a>
            </td>
            <td>
                <a href="<%=request.getContextPath()%>/JobServlet?action=borrar&id=<%=job.getJobId()%>">
                    Borrar
                </a>
            </td>
        </tr>
        <%
                i++;
            }
        %>
    </table>
</div>
<jsp:include page="/includes/footer.jsp"/>
</body>
</html>
<%}%>
<%if(rol.equalsIgnoreCase("Top 3")){%>
<%ArrayList<Department> listaDepartamentos = (ArrayList) session.getAttribute("listaDepartamentos");%>
<!DOCTYPE html>
<html>
<head>
    <title>Lista Departamentos</title>
    <jsp:include page="/includes/headCss.jsp"/>
</head>
<body>
<div class='container'>
    <jsp:include page="/includes/navbar.jsp">
        <jsp:param name="currentPage" value="dep"/>
    </jsp:include>
    <div class="row mb-5 mt-4">
        <div class="col-md-7">
            <h1 class=''>Lista de Departamentos</h1>
        </div>
        <div class="col-md-5 col-lg-4 ms-auto my-auto text-md-end">
            <a href="<%= request.getContextPath()%>/DepartmentServlet?action=formCrear" class="btn btn-primary">
                Crear Departamento</a>
        </div>
    </div>
    <jsp:include page="/includes/infoMsgs.jsp"/>
    <table class="table">
        <tr>
            <th>Department ID</th>
            <th>Department name</th>
            <th>Manager</th>
            <th>Location</th>
            <th></th>
            <th></th>
        </tr>
        <%
            for (Department department : listaDepartamentos) {
        %>
        <tr>
            <td><%=department.getDepartmentId()%>
            </td>
            <td><%=department.getDepartmentName()%>
            </td>
            <td><%=department.getManager() == null ? "--" : (department.getManager().getFirstName() + " " + department.getManager().getLastName()) %>
            </td>
            <td><%=department.getLocation() == null ? "--" : department.getLocation().getCity()%>
            </td>
            <td>
                <a href="<%=request.getContextPath()%>/DepartmentServlet?action=editar&id=<%=department.getDepartmentId()%>">
                    Editar
                </a>
            </td>
            <td>
                <a href="<%=request.getContextPath()%>/DepartmentServlet?action=borrar&id=<%=department.getDepartmentId()%>">
                    Borrar
                </a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>
<jsp:include page="/includes/footer.jsp"/>
</body>
</html>
<%}%>
<%if(rol.equalsIgnoreCase("Top 4")){%>
<%ArrayList<Country> countries = (ArrayList) session.getAttribute("listaPaises");%>
<!DOCTYPE html>
<html>
<head>
    <title>Lista empleados</title>
    <jsp:include page="includes/headCss.jsp"/>
</head>
<body>
<div class='container'>
    <jsp:include page="/includes/navbar.jsp">
        <jsp:param name="currentPage" value="cou"/>
    </jsp:include>
    <div class="row mb-5 mt-4">
        <div class="col-md-7">
            <h1>Lista de Paises</h1>
        </div>
        <div class="col-md-5 col-lg-4 ms-auto my-auto text-md-end">
            <a href="<%=request.getContextPath()%>/CountryServlet?action=formCrear" class="btn btn-primary">
                Crear Pais</a>
        </div>
    </div>
    <jsp:include page="/includes/infoMsgs.jsp"/>
    <table class="table">
        <tr>
            <th>#</th>
            <th>Country ID</th>
            <th>Country name</th>
            <th>Region ID</th>
            <th></th>
            <th></th>
        </tr>
        <%
            int i = 1;
            for (Country country : countries) {
        %>
        <tr>
            <td><%=i%>
            </td>
            <td><%=country.getCountryId()%>
            </td>
            <td><%=country.getCountryName() %>
            </td>
            <td><%=country.getRegionId()%>
            </td>
            <td>
                <a href="<%=request.getContextPath()%>/CountryServlet?action=editar&id=<%=country.getCountryId()%>">
                    Editar
                </a>
            </td>
            <td>
                <a href="<%=request.getContextPath()%>/CountryServlet?action=borrar&id=<%=country.getCountryId()%>">
                    Borrar
                </a>
            </td>
        </tr>
        <%
                i++;
            }
        %>
    </table>
</div>
<jsp:include page="/includes/footer.jsp"/>
</body>
</html>
<%}%>

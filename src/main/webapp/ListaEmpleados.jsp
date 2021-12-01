<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="pe.edu.pucp.iweb.lab10.Beans.Employee" %>
<%@ page import="java.util.ArrayList" %>
<%ArrayList<Employee> employeeList = (ArrayList) request.getAttribute("listaEmpleados");%>
<!DOCTYPE html>
<html>
<head>
    <title>Lista empleados</title>
    <jsp:include page="/includes/headCss.jsp"/>
</head>
<body>
<div class='container'>
    <jsp:include page="/includes/navbar.jsp">
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
            <td><%=i%>
            </td>
            <td><%= e.getFirstName() + " " + e.getLastName()%>
            </td>
            <td><%= e.getEmail()%>
            </td>
            <td><%= e.getJob().getJobTitle()%>
            </td>
            <td><%= e.getSalary()%>
            </td>
            <td><%=e.getCommissionPct()%>
            </td>
            <td><%=e.getManager().getEmployeeId()%>
            </td>
            <td><%=e.getDepartment().getDepartmentId() != null ? e.getDepartment().getDepartmentId() : ""%>
            </td>
        </tr>
        <%
                i++;
            }
        %>
        </tbody>
    </table>
    <jsp:include page="/includes/footer.jsp"/>
</div>
</body>
</html>

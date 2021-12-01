<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="pe.edu.pucp.iweb.lab10.Beans.Job" %>
<%@ page import="java.util.ArrayList" %>
<%ArrayList<Job> listaTrabajos =(ArrayList) request.getAttribute("listaTrabajos");%>
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
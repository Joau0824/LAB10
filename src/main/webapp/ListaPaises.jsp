<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="pe.edu.pucp.iweb.lab10.Beans.Country" %>
<%@ page import="java.util.ArrayList" %>
<%ArrayList<Country> countries = (ArrayList) request.getAttribute("listaPaises");%>
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
<%@ page language="java"
import="ua.module7.hibernate.models.*"
%>

<!DOCTYPE html>
<html>
<head>
    <title>Reports page</title>
    <jsp:include page="headers.jsp"/>
</head>
<body>
    <div class="container">
        <jsp:include page="navigation.jsp"/>
        <div class="row">
            <h2>Report "Projects"</h2>
        </div>

        <div class="row">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Creation date</th>
                    <th scope="col">Number developers</th>
                </tr>
                </thead>
                <tbody>
                <%
                ModelsList modelsList = (ModelsList) request.getAttribute("modelsList");
                for(DbModel dbModel : modelsList) {
                    ReportProjects model = (ReportProjects) dbModel;%>
                     <tr>
                        <td><%= model.getProject_id() %></td>
                        <td><%= model.getProject_name() %></td>
                        <td><%= model.getCreation_date() %></td>
                        <td><%= model.getNumber_developers() %></td>
                     </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
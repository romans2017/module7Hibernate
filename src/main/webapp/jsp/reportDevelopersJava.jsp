<%@ page language="java"
import="ua.module6.projectsystem.models.*"
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
            <h2>Report "Java developers"</h2>
        </div>

        <div class="row">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Age</th>
                    <th scope="col">Language</th>
                    <th scope="col">Level</th>
                    <th scope="col">Salary</th>
                </tr>
                </thead>
                <tbody>
                <%
                ModelsList modelsList = (ModelsList) request.getAttribute("modelsList");
                for(DbModel dbModel : modelsList) {
                    ReportDevelopers model = (ReportDevelopers) dbModel;%>
                     <tr>
                        <td><%= model.getDeveloper_id() %></td>
                        <td><%= model.getDeveloper_name() %></td>
                        <td><%= model.getAge() %></td>
                        <td><%= model.getLanguage() %></td>
                        <td><%= model.getLevel() %></td>
                        <td><%= model.getSalary() %></td>
                     </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
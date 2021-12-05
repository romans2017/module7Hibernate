<%@ page language="java"
import="ua.module7.hibernate.models.*"
%>
<%
ModelsList projectList = (ModelsList) request.getAttribute("projectList");
ModelsList modelsList = (ModelsList) request.getAttribute("modelsList");
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
            <h2>Report "Developers by project"</h2>
        </div>

        <div class="row">
             <form action="/reportDevelopersProjects/view" method="POST">
                <div class="input-group mb-3">
                    <select class="form-control" name="project_id" id="project_id">
                        <%
                        for (DbModel modelProject : projectList) {
                            Project project = (Project) modelProject; %>
                            <option value="<%= project.getId()%>"><%= project.getName()%></option>
                        <% } %>
                    </select>
                    <input type="submit" class="btn btn-primary" value="Show"/>
                </div>
            </form>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Project Id</th>
                    <th scope="col">Project name</th>
                    <th scope="col">Developer Id</th>
                    <th scope="col">Developer</th>
                    <th scope="col">Age</th>
                    <th scope="col">Salary</th>
                </tr>
                </thead>
                <tbody>
                <%
                for(DbModel dbModel : modelsList) {
                    ReportDevelopersProjects model = (ReportDevelopersProjects) dbModel;%>
                     <tr>
                        <td><%= model.getProject_id() %></td>
                        <td><%= model.getProject_name() %></td>
                        <td><%= model.getDeveloper_id() %></td>
                        <td><%= model.getDeveloper_name() %></td>
                        <td><%= model.getAge() %></td>
                        <td><%= model.getSalary() %></td>
                     </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
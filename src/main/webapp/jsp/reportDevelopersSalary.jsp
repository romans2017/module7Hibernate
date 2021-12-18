<%@ page language="java"
import="ua.module7.hibernate.pojo.*, java.util.List, java.util.Optional"
%>
<%
List<Developer> modelsList = (List<Developer>) request.getAttribute("modelsList");
List<Project> projectList = (List<Project>) request.getAttribute("projectList");
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
            <h2>Report "<%= request.getAttribute("title")%>"</h2>
        </div>

        <div class="row">
             <form action="${pageContext.request.contextPath}/reportDevelopers/<%= request.getAttribute("localUrl")%>/view" method="POST" id="selectProject">
                <div class="input-group mb-3">
                    <select class="form-control" name="project_id" id="project_id" form="selectProject">
                        <%
                        for (Project project : projectList) { %>
                            <option value="<%= project.getId()%>"><%= project.getName()%></option>
                        <% } %>
                    </select>
                    <input type="submit" class="btn btn-primary" value="Show" form="selectProject"/>
                </div>
            </form>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Age</th>
                    <th scope="col">Company</th>
                    <th scope="col">Salary</th>
                </tr>
                </thead>
                <tbody>
                <%
                for(Developer model : modelsList) {%>
                     <tr>
                        <td><%= model.getId() %></td>
                        <td><%= model.getName() %></td>
                        <td><%= model.getAge() %></td>
                        <td><%= Optional.ofNullable(model.getCompany()).map(Company::getName).orElse("empty") %></td>
                        <td><%= model.getSalary() %></td>
                     </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
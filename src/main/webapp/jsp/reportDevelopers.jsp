<%@ page language="java"
import="ua.module7.hibernate.pojo.*, java.util.List, java.util.Optional"
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
                List<Pojo> modelsList = (List<Pojo>) request.getAttribute("modelsList");
                for(Pojo dbModel : modelsList) {
                    Developer model = (Developer) dbModel;%>
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
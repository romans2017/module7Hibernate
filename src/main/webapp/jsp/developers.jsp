<%@ page language="java"
import="ua.module7.hibernate.pojo.*, java.util.List, java.util.Optional"
%>

<!DOCTYPE html>
<html>
<head>
    <title>Developers page</title>
    <jsp:include page="headers.jsp"/>
</head>
<body>
    <div class="container">
        <jsp:include page="navigation.jsp"/>
        <div class="row">
            <h2>DEVELOPERS</h2>
        </div>

        <div class="row">
            <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                <div class="btn-group me-2" role="group" aria-label="Second group">
                    <form action="${pageContext.request.contextPath}/developers/new" method="POST" id="newForm">
                        <input hidden type="text" name="id" value="0" form="newForm"/>
                        <input type="submit" class="btn btn-primary" value="New" form="newForm"/>
                    </form>
                </div>
            </div>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Age</th>
                    <th scope="col">Company</th>
                    <th scope="col">Salary</th>
                    <th scope="col">Action</th>
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
                        <td>
                            <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                <div class="btn-group me-2" role="group" aria-label="Second group">
                                    <form action="${pageContext.request.contextPath}/developers/edit" method="POST" id="editForm<%= model.getId() %>">
                                        <input hidden type="text" name="id" value="<%= model.getId() %>" form="editForm<%= model.getId() %>"/>
                                        <input type="submit" class="btn btn-warning" value="Edit" form="editForm<%= model.getId() %>"/>
                                    </form>
                                    <form action="${pageContext.request.contextPath}/developers/remove" method="POST" id="removeForm<%= model.getId() %>">
                                        <input hidden type="text" name="id" value="<%= model.getId() %>" form="removeForm<%= model.getId() %>"/>
                                        <input type="submit" class="btn btn-danger" value="Remove" form="removeForm<%= model.getId() %>"/>
                                    </form>
                                </div>
                            </div>
                        </td>
                     </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
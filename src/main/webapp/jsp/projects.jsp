<%@ page language="java"
import="ua.module6.projectsystem.models.*"
%>

<!DOCTYPE html>
<html>
<head>
    <title>Projects page</title>
    <jsp:include page="headers.jsp"/>
</head>
<body>
    <div class="container">
        <jsp:include page="navigation.jsp"/>
        <div class="row">
            <h2>PROJECTS</h2>
        </div>

        <div class="row">
            <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                <div class="btn-group me-2" role="group" aria-label="Second group">
                    <form action="/projects/new" method="POST">
                        <input hidden type="text" name="id" value="0"/>
                        <input type="submit" class="btn btn-primary" value="New"/>
                    </form>
                </div>
            </div>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Description</th>
                    <th scope="col">Creation date</th>
                    <th scope="col">Cost</th>
                    <th scope="col">Company</th>
                    <th scope="col">Customer</th>
                    <th scope="col">Action</th>
                </tr>
                </thead>
                <tbody>
                <%
                ModelsList modelsList = (ModelsList) request.getAttribute("modelsList");
                for(DbModel dbModel : modelsList) {
                    Project model = (Project) dbModel;%>
                     <tr>
                        <td><%= model.getId() %></td>
                        <td><%= model.getName() %></td>
                        <td><%= model.getDescription() %></td>
                        <td><%= model.getCreation_date() %></td>
                        <td><%= model.getCost() %></td>
                        <td><%= model.getCompany_name() %></td>
                        <td><%= model.getCustomer_name() %></td>
                        <td>
                            <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                <div class="btn-group me-2" role="group" aria-label="Second group">
                                    <form action="/projects/edit" method="POST">
                                        <input hidden type="text" name="id" value="<%= model.getId() %>"/>
                                        <input type="submit" class="btn btn-warning" value="Edit"/>
                                    </form>
                                    <form action="/projects/remove" method="POST">
                                        <input hidden type="text" name="id" value="<%= model.getId() %>"/>
                                        <input type="submit" class="btn btn-danger" value="Remove" />
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
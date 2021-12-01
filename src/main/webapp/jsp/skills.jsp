<%@ page language="java"
import="ua.module6.projectsystem.models.*"
%>

<!DOCTYPE html>
<html>
<head>
    <title>Skills page</title>
    <jsp:include page="headers.jsp"/>
</head>
<body>
    <div class="container">
        <jsp:include page="navigation.jsp"/>
        <div class="row">
            <h2>SKILLS</h2>
        </div>

        <div class="row">
            <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                <div class="btn-group me-2" role="group" aria-label="Second group">
                    <form action="/skills/new" method="POST">
                        <input hidden type="text" name="id" value="0"/>
                        <input type="submit" class="btn btn-primary" value="New"/>
                    </form>
                </div>
            </div>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Language</th>
                    <th scope="col">Level</th>
                    <th scope="col">Action</th>
                </tr>
                </thead>
                <tbody>
                <%
                ModelsList modelsList = (ModelsList) request.getAttribute("modelsList");
                for(DbModel dbModel : modelsList) {
                    Skill model = (Skill) dbModel;%>
                     <tr>
                        <td><%= model.getId() %></td>
                        <td><%= model.getLanguage() %></td>
                        <td><%= model.getLevel() %></td>
                        <td>
                            <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                <div class="btn-group me-2" role="group" aria-label="Second group">
                                    <form action="/skills/edit" method="POST">
                                        <input hidden type="text" name="id" value="<%= model.getId() %>"/>
                                        <input type="submit" class="btn btn-warning" value="Edit"/>
                                    </form>
                                    <form action="/skills/remove" method="POST">
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
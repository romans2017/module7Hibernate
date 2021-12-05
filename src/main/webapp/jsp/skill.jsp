<%@ page language="java"
import="ua.module7.hibernate.models.*"
%>
<% Skill model = (Skill) request.getAttribute("model");
ModelsList developerList = (ModelsList) request.getAttribute("developerList");
ModelsList developersSkills = (ModelsList) request.getAttribute("developersSkills");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Skills page</title>
    <jsp:include page="headers.jsp"/>
</head>
<body>
    <div class="container">
        <div class="row">
            <h2>SKILL</h2>
        </div>

        <div class="row">
            <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                <div class="btn-group me-2" role="group" aria-label="Second group">
                    <form action="/skills/save" method="POST">
                        <input type="submit" class="btn btn-primary" value="Save"/>
                        <input type="submit" formaction = "/skills" formmethod = "GET" class="btn btn-success" value="Back..."/>
                        <div class="mb-3">
                            <label for="id" class="form-label">ID</label>
                            <input type="text" readonly class="form-control"
                                   value="<%= model.getId()%>"
                                   name="id" id="id" placeholder="Id">
                        </div>
                        <div class="mb-3">
                            <label for="language" class="form-label">Language</label>
                            <input type="text" class="form-control"
                                   value="<%= model.getLanguage()%>"
                                   name="language" id="language" placeholder="Language">
                        </div>
                        <div class="mb-3">
                            <label for="level" class="form-label">Level</label>
                            <input type="text" class="form-control"
                                   value="<%= model.getLevel()%>"
                                   name="level" id="level" placeholder="Level">
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div>
            <br>
            <br>
            <div class="row">
                <h4>Developers</h4>
                <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                    <div class="btn-group me-2" role="group" aria-label="Second group">
                        <form action="/skills/addDeveloper" method="POST">
                            <div class="input-group mb-3">
                                <input hidden type="text" name="skill_id" value="<%= model.getId()%>"/>
                                <select class="form-control" name="developer_id" id="developer_id">
                                    <%
                                    for (DbModel modelProject : developerList) {
                                        Developer developer = (Developer) modelProject; %>
                                        <option value="<%= developer.getId()%>"><%= developer.getName()%></option>
                                    <% } %>
                                </select>
                                <input type="submit" class="btn btn-primary" value="Add"/>
                            </div>
                        </form>
                    </div>
                </div>
                <table class="table table-sm">
                    <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Name</th>
                        <th scope="col">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                    for(DbModel dbModel : developersSkills) {
                        Developer developer = (Developer) dbModel;%>
                         <tr>
                            <td><%= developer.getId() %></td>
                            <td><%= developer.getName() %></td>
                            <td>
                                <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                    <div class="btn-group me-2" role="group" aria-label="Second group">
                                        <form action="/skills/removeDeveloper" method="POST">
                                            <input hidden type="text" name="skill_id" value="<%= model.getId()%>"/>
                                            <input hidden type="text" name="id" value="<%= developer.getId()%>"/>
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
    </div>
</body>
</html>
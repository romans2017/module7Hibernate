<%@ page language="java"
import="ua.module7.hibernate.pojo.*, java.util.List"
%>
<% Skill model = (Skill) request.getAttribute("model");
List<Developer> developerList = (List<Developer>) request.getAttribute("developerList");
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
                    <form action="${pageContext.request.contextPath}/skills/save" method="POST"  id="saveForm">
                        <input type="submit" class="btn btn-primary" value="Save" form="saveForm"/>
                        <input type="submit" formaction = "${pageContext.request.contextPath}/skills" formmethod = "GET" class="btn btn-success" value="Back..." form="saveForm"/>
                        <div class="mb-3">
                            <label for="id" class="form-label">ID</label>
                            <input type="text" readonly class="form-control"
                                   value="<%= model.getId()%>"
                                   name="id" id="id" placeholder="Id" form="saveForm">
                        </div>
                        <div class="mb-3">
                            <label for="language" class="form-label">Language</label>
                            <input type="text" class="form-control"
                                   value="<%= model.getLanguage()%>"
                                   name="language" id="language" placeholder="Language" form="saveForm" onchange="skill_language.value = this.value">
                        </div>
                        <div class="mb-3">
                            <label for="level" class="form-label">Level</label>
                            <input type="text" class="form-control"
                                   value="<%= model.getLevel()%>"
                                   name="level" id="level" placeholder="Level" form="saveForm" onchange="skill_level.value = this.value">
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
                        <form action="${pageContext.request.contextPath}/skills/addDeveloper" method="POST" id="addDeveloperForm">
                            <div class="input-group mb-3">
                                <input hidden type="text" name="skill_id" value="<%= model.getId()%>" form="addDeveloperForm"/>
                                <input hidden type="text" id="skill_language" name="language" value="<%= model.getLanguage()%>" form="addDeveloperForm"/>
                                <input hidden type="text" id="skill_level" name="level" value="<%= model.getLevel()%>" form="addDeveloperForm"/>
                                <select class="form-control" name="developer_id" id="developer_id" form="addDeveloperForm">
                                    <%
                                    for (Developer developer : developerList) { %>
                                        <option value="<%= developer.getId()%>"><%= developer.getName()%></option>
                                    <% } %>
                                </select>
                                <input type="submit" class="btn btn-primary" value="Save & Add" form="addDeveloperForm"/>
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
                    for(Developer developer : model.getDevelopers()) {%>
                         <tr>
                            <td><%= developer.getId() %></td>
                            <td><%= developer.getName() %></td>
                            <td>
                                <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                    <div class="btn-group me-2" role="group" aria-label="Second group">
                                        <form action="${pageContext.request.contextPath}/skills/removeDeveloper" method="POST" id="removeDeveloperForm<%= developer.getId()%>">
                                            <input hidden type="text" name="skill_id" value="<%= model.getId()%>" form="removeDeveloperForm<%= developer.getId()%>"/>
                                            <input hidden type="text" name="developer_id" value="<%= developer.getId()%>" form="removeDeveloperForm<%= developer.getId()%>"/>
                                            <input type="submit" class="btn btn-danger" value="Remove" form="removeDeveloperForm<%= developer.getId()%>"/>
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
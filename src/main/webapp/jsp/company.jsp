<%@ page language="java"
import="ua.module7.hibernate.pojo.*, java.util.List"
%>
<%
Company model = (Company) request.getAttribute("model");
List<Project> projectList = (List<Project>) request.getAttribute("projectList");
List<Developer> developerList = (List<Developer>) request.getAttribute("developerList");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Companies page</title>
    <jsp:include page="headers.jsp"/>
</head>
<body>
    <div class="container">
        <div class="row">
            <h2>COMPANY</h2>
        </div>

        <div class="row">
            <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                <div class="btn-group me-2" role="group" aria-label="Second group">
                    <form action="${pageContext.request.contextPath}/companies/save" method="POST" id="saveForm">
                        <input type="submit" class="btn btn-primary" value="Save" form="saveForm"/>
                        <input type="submit" formaction = "${pageContext.request.contextPath}/companies" formmethod = "GET" class="btn btn-success" value="Back..." form="saveForm"/>
                        <div class="mb-3">
                            <label for="id" class="form-label">ID</label>
                            <input type="text" readonly class="form-control"
                                   value="<%= model.getId()%>"
                                   name="id" id="id" placeholder="Id" form="saveForm">
                        </div>
                        <div class="mb-3">
                            <label for="name" class="form-label">Name</label>
                            <input type="text" class="form-control"
                                   value="<%= model.getName()%>"
                                   name="name" id="name" placeholder="Name" form="saveForm">
                        </div>
                        <div class="mb-3">
                            <label for="country" class="form-label">Country</label>
                            <input type="text" class="form-control"
                                   value="<%= model.getCountry()%>"
                                   name="country" id="country" placeholder="Country" form="saveForm">
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div>
            <br>
            <br>
            <div class="row">
                <h4>Projects</h4>
                <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                    <div class="btn-group me-2" role="group" aria-label="Second group">
                        <form action="${pageContext.request.contextPath}/companies/addProject" method="POST" id="addProjectForm">
                            <div class="input-group mb-3">
                                <input hidden type="text" name="company_id" value="<%= model.getId()%>" form="addProjectForm"/>
                                <select class="form-control" name="project_id" id="project_id" form="addProjectForm">
                                    <%
                                    for (Project project : projectList) { %>
                                        <option value="<%= project.getId()%>"><%= project.getName()%></option>
                                    <% } %>
                                </select>
                                <input type="submit" class="btn btn-primary" value="Add" form="addProjectForm"/>
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
                    for(Project project : model.getProjects()) { %>
                         <tr>
                            <td><%= project.getId() %></td>
                            <td><%= project.getName() %></td>
                            <td>
                                <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                    <div class="btn-group me-2" role="group" aria-label="Second group">
                                        <form action="${pageContext.request.contextPath}/companies/removeProject" method="POST" id="removeProjectForm<%= project.getId()%>">
                                            <input hidden type="text" name="company_id" value="<%= model.getId()%>" form="removeProjectForm<%= project.getId()%>"/>
                                            <input hidden type="text" name="project_id" value="<%= project.getId()%>" form="removeProjectForm<%= project.getId()%>"/>
                                            <input type="submit" class="btn btn-danger" value="Remove" form="removeProjectForm<%= project.getId()%>"/>
                                        </form>
                                    </div>
                                </div>
                            </td>
                         </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>

            <br>
            <div class="row">
                <h4>Developers</h4>
                <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                    <div class="btn-group me-2" role="group" aria-label="Second group">
                        <form action="${pageContext.request.contextPath}/companies/addDeveloper" method="POST" id="addDeveloperForm">
                            <div class="input-group mb-3">
                                <input hidden type="text" name="company_id" value="<%= model.getId()%>" form="addDeveloperForm"/>
                                <select class="form-control" name="developer_id" id="developer_id" form="addDeveloperForm">
                                    <%
                                    for (Developer developer : developerList) { %>
                                        <option value="<%= developer.getId()%>"><%= developer.getName()%></option>
                                    <% } %>
                                </select>
                                <input type="submit" class="btn btn-primary" value="Add" form="addDeveloperForm"/>
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
                                        <form action="${pageContext.request.contextPath}/companies/removeDeveloper" method="POST" id="removeDeveloperForm<%= developer.getId()%>">
                                            <input hidden type="text" name="company_id" value="<%= model.getId()%>" form="removeDeveloperForm<%= developer.getId()%>"/>
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
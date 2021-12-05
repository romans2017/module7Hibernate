<%@ page language="java"
import="ua.module7.hibernate.models.*"
%>
<% Developer model = (Developer) request.getAttribute("model");
ModelsList companyList = (ModelsList) request.getAttribute("companyList");
ModelsList projectList = (ModelsList) request.getAttribute("projectList");
ModelsList skillList = (ModelsList) request.getAttribute("skillList");
ModelsList developersProjects = (ModelsList) request.getAttribute("developersProjects");
ModelsList developersSkills = (ModelsList) request.getAttribute("developersSkills");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Developer page</title>
    <jsp:include page="headers.jsp"/>
</head>
<body>
    <div class="container">
        <div class="row">
            <h2>Developer</h2>
        </div>

        <div class="row">
            <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                <div class="btn-group me-2" role="group" aria-label="Second group">
                    <form action="/developers/save" method="POST">
                        <input type="submit" class="btn btn-primary" value="Save"/>
                        <input type="submit" formaction = "/developers" formmethod = "GET" class="btn btn-success" value="Back..."/>
                        <div class="mb-3">
                            <label for="id" class="form-label">ID</label>
                            <input type="text" readonly class="form-control"
                                   value="<%= model.getId()%>"
                                   name="id" id="id" placeholder="Id">
                        </div>
                        <div class="mb-3">
                            <label for="name" class="form-label">Name</label>
                            <input type="text" class="form-control"
                                   value="<%= model.getName()%>"
                                   name="name" id="name" placeholder="Name">
                        </div>
                        <div class="mb-3">
                            <label for="age" class="form-label">Age</label>
                            <input type="number" class="form-control" min="10" max="200"
                                   value="<%= model.getAge()%>"
                                   name="age" id="age" placeholder="Age">
                        </div>
                        <div class="mb-3">
                            <label for="company_id" class="form-label">Company</label>
                            <select class="form-control" name="company_id" id="company_id">
                                <%
                                for (DbModel modelCompany : companyList) {
                                    Company company = (Company) modelCompany;
                                    if (model.getCompany_id() == company.getId()) { %>
                                        <option selected value="<%= company.getId()%>"><%= company.getName()%></option>
                                    <% } else { %>
                                        <option value="<%= company.getId()%>"><%= company.getName()%></option>
                                    <% } %>
                                <% } %>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="salary" class="form-label">Salary</label>
                            <input type="number" class="form-control" min="0" max="999999999"
                                   value="<%= model.getSalary()%>"
                                   name="salary" id="salary" placeholder="Salary">
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
                        <form action="/developers/addProject" method="POST">
                            <div class="input-group mb-3">
                                <input hidden type="text" name="developer_id" value="<%= model.getId()%>"/>
                                <select class="form-control" name="project_id" id="project_id">
                                    <%
                                    for (DbModel modelProject : projectList) {
                                        Project project = (Project) modelProject; %>
                                        <option value="<%= project.getId()%>"><%= project.getName()%></option>
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
                    for(DbModel dbModel : developersProjects) {
                        Project project = (Project) dbModel;%>
                         <tr>
                            <td><%= project.getId() %></td>
                            <td><%= project.getName() %></td>
                            <td>
                                <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                    <div class="btn-group me-2" role="group" aria-label="Second group">
                                        <form action="/developers/removeProject" method="POST">
                                            <input hidden type="text" name="developer_id" value="<%= model.getId()%>"/>
                                            <input hidden type="text" name="id" value="<%= project.getId()%>"/>
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

            <br>
            <div class="row">
                <h4>Skills</h4>
                <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                    <div class="btn-group me-2" role="group" aria-label="Second group">
                        <form action="/developers/addSkill" method="POST">
                            <div class="input-group mb-3">
                                <input hidden type="text" name="developer_id" value="<%= model.getId()%>"/>
                                <select class="form-control" name="skill_id" id="skill_id">
                                    <%
                                    for (DbModel modelSkill : skillList) {
                                        Skill skill = (Skill) modelSkill; %>
                                        <option value="<%= skill.getId()%>"><%= skill.getLanguage()%>,<%= skill.getLevel()%></option>
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
                        <th scope="col">Language</th>
                        <th scope="col">Level</th>
                        <th scope="col">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                    for(DbModel dbModel : developersSkills) {
                        Skill skill = (Skill) dbModel;%>
                         <tr>
                            <td><%= skill.getId() %></td>
                            <td><%= skill.getLanguage() %></td>
                            <td><%= skill.getLevel() %></td>
                            <td>
                                <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                    <div class="btn-group me-2" role="group" aria-label="Second group">
                                        <form action="/developers/removeSkill" method="POST">
                                            <input hidden type="text" name="developer_id" value="<%= model.getId()%>"/>
                                            <input hidden type="text" name="id" value="<%= skill.getId()%>"/>
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
<%@ page language="java"
import="ua.module7.hibernate.pojo.*, java.util.List, java.util.Optional"
%>
<% Developer model = (Developer) request.getAttribute("model");
List<Company> companyList = (List<Company>) request.getAttribute("companyList");
List<Project> projectList = (List<Project>) request.getAttribute("projectList");
List<Skill> skillList = (List<Skill>) request.getAttribute("skillList");
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
                    <form action="${pageContext.request.contextPath}/developers/save" method="POST" id="saveForm">
                        <input type="submit" class="btn btn-primary" value="Save" form="saveForm"/>
                        <input type="submit" formaction = "${pageContext.request.contextPath}/developers" formmethod = "GET" class="btn btn-success" value="Back..." form="saveForm"/>
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
                            <label for="age" class="form-label">Age</label>
                            <input type="number" class="form-control" min="10" max="200"
                                   value="<%= model.getAge()%>"
                                   name="age" id="age" placeholder="Age" form="saveForm">
                        </div>
                        <div class="mb-3">
                            <label for="company_id" class="form-label">Company</label>
                            <select class="form-control" name="company_id" id="company_id" form="saveForm">
                                <option value="-1">empty</option>
                                <%
                                for (Company company : companyList) {
                                    if (model.getCompany() != null && model.getCompany().getId() == company.getId()) { %>
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
                                   name="salary" id="salary" placeholder="Salary" form="saveForm">
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
                        <form action="${pageContext.request.contextPath}/developers/addProject" method="POST" id="addProjectForm">
                            <div class="input-group mb-3">
                                <input hidden type="text" name="developer_id" value="<%= model.getId()%>" form="addProjectForm"/>
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
                                        <form action="${pageContext.request.contextPath}/developers/removeProject" method="POST" id="removeProjectForm<%= project.getId()%>">
                                            <input hidden type="text" name="developer_id" value="<%= model.getId()%>" form="removeProjectForm<%= project.getId()%>"/>
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
                <h4>Skills</h4>
                <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                    <div class="btn-group me-2" role="group" aria-label="Second group">
                        <form action="${pageContext.request.contextPath}/developers/addSkill" method="POST" id="addSkillForm">
                            <div class="input-group mb-3">
                                <input hidden type="text" name="developer_id" value="<%= model.getId()%>" form="addSkillForm"/>
                                <select class="form-control" name="skill_id" id="skill_id" form="addSkillForm">
                                    <%
                                    for (Skill skill : skillList) { %>
                                        <option value="<%= skill.getId()%>"><%= skill.getLanguage()%>,<%= skill.getLevel()%></option>
                                    <% } %>
                                </select>
                                <input type="submit" class="btn btn-primary" value="Add" form="addSkillForm"/>
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
                    for(Skill skill : model.getSkills()) { %>
                         <tr>
                            <td><%= skill.getId() %></td>
                            <td><%= skill.getLanguage() %></td>
                            <td><%= skill.getLevel() %></td>
                            <td>
                                <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                    <div class="btn-group me-2" role="group" aria-label="Second group">
                                        <form action="${pageContext.request.contextPath}/developers/removeSkill" method="POST" id="removeSkillForm<%= skill.getId()%>">
                                            <input hidden type="text" name="developer_id" value="<%= model.getId()%>" form="removeSkillForm<%= skill.getId()%>"/>
                                            <input hidden type="text" name="skill_id" value="<%= skill.getId()%>" form="removeSkillForm<%= skill.getId()%>"/>
                                            <input type="submit" class="btn btn-danger" value="Remove" form="removeSkillForm<%= skill.getId()%>"/>
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
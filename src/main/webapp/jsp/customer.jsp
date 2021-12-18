<%@ page language="java"
import="ua.module7.hibernate.pojo.*, java.util.List"
%>
<%
Customer model = (Customer) request.getAttribute("model");
List<Project> projectList = (List<Project>) request.getAttribute("projectList");
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
            <h2>CUSTOMER</h2>
        </div>

        <div class="row">
            <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                <div class="btn-group me-2" role="group" aria-label="Second group">
                    <form action="${pageContext.request.contextPath}/customers/save" method="POST" id="saveForm">
                        <input type="submit" class="btn btn-primary" value="Save" form="saveForm"/>
                        <input type="submit" formaction = "${pageContext.request.contextPath}/customers" formmethod = "GET" class="btn btn-success" value="Back..." form="saveForm"/>
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
                        <form action="${pageContext.request.contextPath}/customers/addProject" method="POST" id="addCustomerForm">
                            <div class="input-group mb-3">
                                <input hidden type="text" name="customer_id" value="<%= model.getId()%>" form="addCustomerForm"/>
                                <select class="form-control" name="project_id" id="project_id" form="addCustomerForm">
                                    <%
                                    for (Project project : projectList) { %>
                                        <option value="<%= project.getId()%>"><%= project.getName()%></option>
                                    <% } %>
                                </select>
                                <input type="submit" class="btn btn-primary" value="Add" form="addCustomerForm"/>
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
                                        <form action="${pageContext.request.contextPath}/customers/removeProject" method="POST" id="removeCustomerForm<%= project.getId()%>">
                                            <input hidden type="text" name="customer_id" value="<%= model.getId()%>" form="removeCustomerForm<%= project.getId()%>"/>
                                            <input hidden type="text" name="project_id" value="<%= project.getId()%>" form="removeCustomerForm<%= project.getId()%>"/>
                                            <input type="submit" class="btn btn-danger" value="Remove" form="removeCustomerForm<%= project.getId()%>"/>
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
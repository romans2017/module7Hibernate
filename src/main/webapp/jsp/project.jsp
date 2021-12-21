<%@ page language="java"
import="ua.module7.hibernate.pojo.*, java.util.List, java.util.Optional, java.time.LocalDate"
%>
<% Project model = (Project) request.getAttribute("model");
List<Company> companyList = (List<Company>) request.getAttribute("companyList");
List<Customer> customerList = (List<Customer>) request.getAttribute("customerList");
List<Developer> developerList = (List<Developer>) request.getAttribute("developerList");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Projects page</title>
    <jsp:include page="headers.jsp"/>
</head>
<body>
    <div class="container">
        <div class="row">
            <h2>Project</h2>
        </div>

        <div class="row">
            <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                <div class="btn-group me-2" role="group" aria-label="Second group">
                    <form action="${pageContext.request.contextPath}/projects/save" method="POST" id="saveForm">
                        <input type="submit" class="btn btn-primary" value="Save" form="saveForm"/>
                        <input type="submit" formaction = "${pageContext.request.contextPath}/projects" formmethod = "GET" class="btn btn-success" value="Back..." form="saveForm"/>
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
                                   name="name" id="name" placeholder="Name" form="saveForm" onchange="project_name.value = this.value">
                        </div>
                        <div class="mb-3">
                            <label for="description" class="form-label">Description</label>
                            <input type="text" class="form-control"
                                   value="<%= model.getDescription()%>"
                                   name="description" id="description" placeholder="Description" form="saveForm" onchange="project_description.value = this.value">
                        </div>
                        <div class="mb-3">
                            <label for="creation_date" class="form-label">Creation date</label>
                            <input type="date" class="form-control" required
                                   value=<%= model.getCreationDate()%>
                                   name="creation_date" id="creation_date" placeholder="Creation date" form="saveForm" onchange="project_creation_date.value = this.value">
                        </div>
                        <div class="mb-3">
                            <label for="cost" class="form-label">Cost</label>
                            <input type="number" min="0" max="999999999999" class="form-control"
                                   value="<%= model.getCost()%>"
                                   name="cost" id="cost" placeholder="Cost" form="saveForm" onchange="project_cost.value = this.value">
                        </div>
                        <div class="mb-3">
                            <label for="company_id" class="form-label">Company</label>
                            <select class="form-control" name="company_id" id="company_id" form="saveForm" onchange="project_company.value = this.value">
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
                            <label for="customer_id" class="form-label">Customer</label>
                            <select class="form-control" name="customer_id" id="customer_id" form="saveForm" onchange="project_customer.value = this.value">
                                <option value="-1">empty</option>
                                <%
                                for (Customer customer : customerList) {
                                    if (model.getCustomer() != null && model.getCustomer().getId() == customer.getId()) { %>
                                        <option selected value="<%= customer.getId()%>"><%= customer.getName()%></option>
                                    <% } else { %>
                                        <option value="<%= customer.getId()%>"><%= customer.getName()%></option>
                                    <% } %>
                                <% } %>
                            </select>
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
                        <form action="${pageContext.request.contextPath}/projects/addDeveloper" method="POST" id="addDeveloperForm">
                            <div class="input-group mb-3">
                                <input hidden type="text" name="project_id" value="<%= model.getId()%>" form="addDeveloperForm"/>
                                <input hidden type="text" id="project_name" name="name" value="<%= model.getName()%>" form="addDeveloperForm"/>
                                <input hidden type="text" id="project_description" name="description" value="<%= model.getDescription()%>" form="addDeveloperForm"/>
                                <input hidden type="text" id="project_creation_date" name="creation_date" value="<%= model.getCreationDate()%>" form="addDeveloperForm"/>
                                <input hidden type="text" id="project_cost" name="cost" value="<%= model.getCost()%>" form="addDeveloperForm"/>
                                <% if (model.getCompany() != null) { %>
                                    <input hidden type="text" id="project_company" name="company_id" value="<%= model.getCompany().getId()%>" form="addDeveloperForm"/>
                                <% } else { %>
                                    <input hidden type="text" id="project_company" name="company_id" value="-1" form="addDeveloperForm"/>
                                <% } %>
                                <% if (model.getCustomer() != null) { %>
                                    <input hidden type="text" id="project_customer" name="customer_id" value="<%= model.getCustomer().getId()%>" form="addDeveloperForm"/>
                                <% } else { %>
                                    <input hidden type="text" id="project_customer" name="customer_id" value="-1" form="addDeveloperForm"/>
                                <% } %>
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
                    for(Developer developer : model.getDevelopers()) { %>
                         <tr>
                            <td><%= developer.getId() %></td>
                            <td><%= developer.getName() %></td>
                            <td>
                                <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                    <div class="btn-group me-2" role="group" aria-label="Second group">
                                        <form action="${pageContext.request.contextPath}/projects/removeDeveloper" method="POST" id="removeDeveloperForm<%= developer.getId()%>">
                                            <input hidden type="text" name="project_id" value="<%= model.getId()%>" form="removeDeveloperForm<%= developer.getId()%>"/>
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
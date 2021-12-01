<%@ page language="java"
import="ua.module6.projectsystem.models.*"
%>
<% Project model = (Project) request.getAttribute("model");
ModelsList companyList = (ModelsList) request.getAttribute("companyList");
ModelsList customerList = (ModelsList) request.getAttribute("customerList");
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
                    <form action="/projects/save" method="POST">
                        <input type="submit" class="btn btn-primary" value="Save"/>
                        <input type="submit" formaction = "/projects" formmethod = "GET" class="btn btn-success" value="Back..."/>
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
                            <label for="description" class="form-label">Description</label>
                            <input type="text" class="form-control"
                                   value="<%= model.getDescription()%>"
                                   name="description" id="description" placeholder="Description">
                        </div>
                        <div class="mb-3">
                            <label for="creation_date" class="form-label">Creation date</label>
                            <input type="date" class="form-control"
                                   value="<%= model.getCreation_date()%>"
                                   name="creation_date" id="creation_date" placeholder="Creation date">
                        </div>
                        <div class="mb-3">
                            <label for="cost" class="form-label">Cost</label>
                            <input type="number" min="0" max="999999999999" class="form-control"
                                   value="<%= model.getCost()%>"
                                   name="cost" id="cost" placeholder="Cost">
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
                            <label for="customer_id" class="form-label">Company</label>
                            <select class="form-control" name="customer_id" id="customer_id">
                                <%
                                for (DbModel modelCompany : customerList) {
                                    Customer customer = (Customer) modelCompany;
                                    if (model.getCustomer_id() == customer.getId()) { %>
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
    </div>
</body>
</html>
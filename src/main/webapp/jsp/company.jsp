<%@ page language="java"
import="ua.module7.hibernate.models.*"
%>
<% Company model = (Company) request.getAttribute("model"); %>

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
                    <form action="/companies/save" method="POST">
                        <input type="submit" class="btn btn-primary" value="Save"/>
                        <input type="submit" formaction = "/companies" formmethod = "GET" class="btn btn-success" value="Back..."/>
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
                            <label for="country" class="form-label">Country</label>
                            <input type="text" class="form-control"
                                   value="<%= model.getCountry()%>"
                                   name="country" id="country" placeholder="Country">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
<%@ page language="java"
import="ua.module7.hibernate.pojo.*, java.util.List"
%>

<!DOCTYPE html>
<html>
<head>
    <title>Customers page</title>
    <jsp:include page="headers.jsp"/>
</head>
<body>
    <div class="container">
        <jsp:include page="navigation.jsp"/>
        <div class="row">
            <h2>CUSTOMERS</h2>
        </div>

        <div class="row">
            <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                <div class="btn-group me-2" role="group" aria-label="Second group">
                    <form action="${pageContext.request.contextPath}/customers/new" method="POST" id="newForm">
                        <input hidden type="text" name="id" value="0" form="newForm"/>
                        <input type="submit" class="btn btn-primary" value="New" form="newForm"/>
                    </form>
                </div>
            </div>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Country</th>
                    <th scope="col">Action</th>
                </tr>
                </thead>
                <tbody>
                <%
                List<Pojo> modelsList = (List<Pojo>) request.getAttribute("modelsList");
                for(Pojo dbModel : modelsList) {
                    Customer model = (Customer) dbModel;%>
                     <tr>
                        <td><%= model.getId() %></td>
                        <td><%= model.getName() %></td>
                        <td><%= model.getCountry() %></td>
                        <td>
                            <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                <div class="btn-group me-2" role="group" aria-label="Second group">
                                    <form action="${pageContext.request.contextPath}/customers/edit" method="POST" id="editForm<%= model.getId() %>">
                                        <input hidden type="text" name="id" value="<%= model.getId() %>" form="editForm<%= model.getId() %>"/>
                                        <input type="submit" class="btn btn-warning" value="Edit" form="editForm<%= model.getId() %>"/>
                                    </form>
                                    <form action="${pageContext.request.contextPath}/customers/remove" method="POST" id="removeForm<%= model.getId() %>">
                                        <input hidden type="text" name="id" value="<%= model.getId() %>" form="removeForm<%= model.getId() %>"/>
                                        <input type="submit" class="btn btn-danger" value="Remove" form="removeForm<%= model.getId() %>"/>
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
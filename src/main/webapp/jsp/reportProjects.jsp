<%@ page language="java"
import="ua.module7.hibernate.pojo.*, java.util.List"
%>

<!DOCTYPE html>
<html>
<head>
    <title>Reports page</title>
    <jsp:include page="headers.jsp"/>
</head>
<body>
    <div class="container">
        <jsp:include page="navigation.jsp"/>
        <div class="row">
            <h2>Report "Projects"</h2>
        </div>

        <div class="row">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Creation date</th>
                    <th scope="col">Number developers</th>
                </tr>
                </thead>
                <tbody>
                <%
                List<Pojo> modelsList = (List<Pojo>) request.getAttribute("modelsList");
                for(Pojo dbModel : modelsList) {
                    Project model = (Project) dbModel;%>
                     <tr>
                        <td><%= model.getId() %></td>
                        <td><%= model.getName() %></td>
                        <td><%= model.getCreationDateFormatted() %></td>
                        <td><%= model.getDevelopers().size() %></td>
                     </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
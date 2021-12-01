<%@ page language="java"
import="ua.module6.projectsystem.models.*"
%>
<% Skill model = (Skill) request.getAttribute("model"); %>

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
                    <form action="/skills/save" method="POST">
                        <input type="submit" class="btn btn-primary" value="Save"/>
                        <input type="submit" formaction = "/skills" formmethod = "GET" class="btn btn-success" value="Back..."/>
                        <div class="mb-3">
                            <label for="id" class="form-label">ID</label>
                            <input type="text" readonly class="form-control"
                                   value="<%= model.getId()%>"
                                   name="id" id="id" placeholder="Id">
                        </div>
                        <div class="mb-3">
                            <label for="language" class="form-label">Language</label>
                            <input type="text" class="form-control"
                                   value="<%= model.getLanguage()%>"
                                   name="language" id="language" placeholder="Language">
                        </div>
                        <div class="mb-3">
                            <label for="level" class="form-label">Level</label>
                            <input type="text" class="form-control"
                                   value="<%= model.getLevel()%>"
                                   name="level" id="level" placeholder="Level">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
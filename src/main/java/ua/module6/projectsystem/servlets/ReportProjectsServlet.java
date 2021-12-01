package ua.module6.projectsystem.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import ua.module6.projectsystem.models.ReportProjects;
import ua.module6.projectsystem.queries.Query;

@WebServlet("/reportProjects")
public class ReportProjectsServlet extends AbstractServlet {

    @Override
    public void init() {
        this.serviceQuery = (Query) getServletContext().getAttribute("reportProjectsQuery");
        this.classDbModel = ReportProjects.class;
        this.jspView = "reportProjects.jsp";
        this.jspEdit = "";
        this.redirectPath = "";
    }

    protected void createEditModel(HttpServletRequest req) throws NumberFormatException {}
}

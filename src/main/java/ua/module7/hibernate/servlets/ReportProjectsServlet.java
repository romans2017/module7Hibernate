package ua.module7.hibernate.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import ua.module7.hibernate.models.ReportProjects;
import ua.module7.hibernate.queries.Query;

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

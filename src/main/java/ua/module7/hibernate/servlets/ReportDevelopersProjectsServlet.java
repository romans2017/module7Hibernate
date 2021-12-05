package ua.module7.hibernate.servlets;

import jakarta.servlet.annotation.WebServlet;
import ua.module7.hibernate.models.ReportDevelopersProjects;
import ua.module7.hibernate.queries.Query;

@WebServlet("/reportDevelopersProjects/*")
public class ReportDevelopersProjectsServlet extends ReportDevelopersSalaryServlet {

    @Override
    public void init() {
        super.init();
        this.serviceQuery = (Query) getServletContext().getAttribute("reportDevelopersProjectsQuery");
        this.classDbModel = ReportDevelopersProjects.class;
        this.jspView = "reportDevelopersProjects.jsp";
    }
}

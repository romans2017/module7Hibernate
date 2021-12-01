package ua.module6.projectsystem.servlets;

import jakarta.servlet.annotation.WebServlet;
import ua.module6.projectsystem.models.ReportDevelopersProjects;
import ua.module6.projectsystem.queries.Query;

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

package ua.module6.projectsystem.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import ua.module6.projectsystem.models.ReportDevelopers;
import ua.module6.projectsystem.queries.Query;

@WebServlet("/reportDevelopersJava")
public class ReportDevelopersJavaServlet extends AbstractServlet {

    @Override
    public void init() {
        this.serviceQuery = (Query) getServletContext().getAttribute("reportDevelopersJavaQuery");
        this.classDbModel = ReportDevelopers.class;
        this.jspView = "reportDevelopersJava.jsp";
        this.jspEdit = "";
        this.redirectPath = "";
    }

    protected void createEditModel(HttpServletRequest req) throws NumberFormatException {}
}

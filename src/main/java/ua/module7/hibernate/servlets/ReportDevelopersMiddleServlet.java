package ua.module7.hibernate.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import ua.module7.hibernate.models.ReportDevelopers;
import ua.module7.hibernate.queries.Query;

@WebServlet("/reportDevelopersMiddle")
public class ReportDevelopersMiddleServlet extends AbstractServlet {

    @Override
    public void init() {
        this.serviceQuery = (Query) getServletContext().getAttribute("reportDevelopersMiddleQuery");
        this.classDbModel = ReportDevelopers.class;
        this.jspView = "reportDevelopersMiddle.jsp";
        this.jspEdit = "";
        this.redirectPath = "";
    }

    protected void createEditModel(HttpServletRequest req) throws NumberFormatException {}
}

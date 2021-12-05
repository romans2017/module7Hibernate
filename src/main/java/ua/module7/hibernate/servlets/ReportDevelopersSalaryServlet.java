package ua.module7.hibernate.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.module7.hibernate.models.ModelsList;
import ua.module7.hibernate.models.ReportDevelopersSalary;
import ua.module7.hibernate.queries.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/reportDevelopersSalary/*")
public class ReportDevelopersSalaryServlet extends AbstractServlet {

    private Query serviceQueryProjects;

    @Override
    public void init() {
        this.serviceQuery = (Query) getServletContext().getAttribute("reportDevelopersSalaryQuery");
        this.serviceQueryProjects = (Query) getServletContext().getAttribute("projectQuery");
        this.classDbModel = ReportDevelopersSalary.class;
        this.jspView = "reportDevelopersSalary.jsp";
        this.jspEdit = "";
        this.redirectPath = "";
    }

    private void viewReport(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("projectList", getAllModels(serviceQueryProjects));
        req.getRequestDispatcher("/jsp/" + jspView).forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("modelsList", new ModelsList());
        viewReport(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("modelsList", serviceQuery.get(Map.of("project_id", Integer.parseInt(req.getParameter("project_id")))));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        viewReport(req, resp);
    }

    protected void createEditModel(HttpServletRequest req) throws NumberFormatException {}
}

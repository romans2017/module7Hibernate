package ua.module7.hibernate.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.module7.hibernate.dao.Dao;
import ua.module7.hibernate.dao.DeveloperDao;
import ua.module7.hibernate.pojo.Developer;
import ua.module7.hibernate.pojo.Project;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/reportDevelopers/salary/*")
public class ReportDevelopersSalaryServlet<E> extends AbstractServlet<Developer> {

    protected Dao<Project> serviceDaoProjects;
    protected String title;

    @Override
    @SuppressWarnings("unchecked")
    public void init() throws ServletException {
        super.init();
        this.serviceDao = (Dao<Developer>) getServletContext().getAttribute("developerDao");
        this.serviceDaoProjects = (Dao<Project>) getServletContext().getAttribute("projectDao");
        this.jspView = "reportDevelopersSalary.jsp";
        this.jspEdit = "";
        this.redirectPath = "salary";
        this.title = "Developers' salary by project";
    }

    protected void viewReport(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("projectList", ServletService.getAllModels(serviceDaoProjects, Project.class));
        req.setAttribute("title", title);
        req.setAttribute("localUrl", redirectPath);
        req.getRequestDispatcher("/jsp/" + jspView).forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("modelsList", new ArrayList<Developer>() {});
        viewReport(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("modelsList", ((DeveloperDao) serviceDao).readDevelopersByProject(Integer.parseInt(req.getParameter("project_id"))));
        viewReport(req, resp);
    }

    @Override
    protected void createUpdateModel(HttpServletRequest req) throws NumberFormatException {
    }

    @Override
    protected void postEditRequest(Developer model, HttpServletRequest req, HttpServletResponse resp) {
    }
}

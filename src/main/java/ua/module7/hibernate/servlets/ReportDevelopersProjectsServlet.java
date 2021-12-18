package ua.module7.hibernate.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.module7.hibernate.pojo.Developer;
import ua.module7.hibernate.pojo.Project;

import java.io.IOException;

@WebServlet("/reportDevelopers/project/*")
public class ReportDevelopersProjectsServlet extends ReportDevelopersSalaryServlet<Developer> {

    @Override
    public void init() throws ServletException {
        super.init();
        this.redirectPath = "project";
        this.title = "Developers by project";
    }

    @Override
    protected void viewReport(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("projectList", ServletService.getAllModels(serviceDaoProjects, Project.class));
        req.setAttribute("title", title);
        req.setAttribute("localUrl", redirectPath);
        req.getRequestDispatcher("/jsp/" + jspView).forward(req, resp);
    }
}

package ua.module7.hibernate.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.module7.hibernate.dao.Dao;
import ua.module7.hibernate.pojo.Project;


@WebServlet("/reportProjects")
public class ReportProjectsServlet extends AbstractServlet<Project> {

    @Override
    @SuppressWarnings("unchecked")
    public void init() throws ServletException {
        super.init();
        this.serviceDao = (Dao<Project>) getServletContext().getAttribute("projectDao");
        this.jspView = "reportProjects.jsp";
        this.jspEdit = "";
        this.redirectPath = "";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    }

    @Override
    protected void createUpdateModel(HttpServletRequest req) throws NumberFormatException {
    }

    @Override
    protected void postEditRequest(Project model, HttpServletRequest req, HttpServletResponse resp) {
    }
}

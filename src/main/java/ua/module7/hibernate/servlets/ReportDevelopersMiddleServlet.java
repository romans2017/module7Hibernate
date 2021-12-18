package ua.module7.hibernate.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.module7.hibernate.dao.Dao;
import ua.module7.hibernate.dao.DeveloperDao;
import ua.module7.hibernate.pojo.Developer;

import java.io.IOException;

@WebServlet("/reportDevelopers/middle")
public class ReportDevelopersMiddleServlet extends ReportDevelopersJavaServlet<Developer> {

    @Override
    @SuppressWarnings("unchecked")
    public void init() throws ServletException {
        super.init();
        this.serviceDao = (Dao<Developer>) getServletContext().getAttribute("developerDao");
        this.jspView = "reportDevelopers.jsp";
        this.jspEdit = "";
        this.redirectPath = "";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("modelsList", ((DeveloperDao) serviceDao).readMiddleDevelopers());
        req.setAttribute("title", "Middle-level developers");
        req.getRequestDispatcher("/jsp/" + jspView).forward(req, resp);
    }

}

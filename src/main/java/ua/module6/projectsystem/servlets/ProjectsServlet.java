package ua.module6.projectsystem.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.module6.projectsystem.models.DbModel;
import ua.module6.projectsystem.models.Project;
import ua.module6.projectsystem.queries.Query;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/projects/*")
public class ProjectsServlet extends AbstractServlet {

    private Query serviceQueryCompany;
    private Query serviceQueryCustomer;

    @Override
    public void init() {
        this.serviceQuery = (Query) getServletContext().getAttribute("projectQuery");
        this.serviceQueryCompany = (Query) getServletContext().getAttribute("companyQuery");
        this.serviceQueryCustomer = (Query) getServletContext().getAttribute("customerQuery");
        this.classDbModel = Project.class;
        this.jspView = "projects.jsp";
        this.jspEdit = "project.jsp";
        this.redirectPath = "projects";
    }

    protected void createEditModel(HttpServletRequest req) throws NumberFormatException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Project project = new Project();
        project.setId(id);
        project.setName(req.getParameter("name"));
        project.setCost(Integer.parseInt(req.getParameter("cost")));
        project.setDescription(req.getParameter("description"));
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        Date docDate = null;
        try {
            docDate = format.parse(req.getParameter("creation_date"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        project.setCreation_date(docDate);
        project.setCustomer_id(Integer.parseInt(req.getParameter("customer_id")));
        project.setCompany_id(Integer.parseInt(req.getParameter("company_id")));
        if (id == 0) {
            serviceQuery.create(project);
        } else {
            serviceQuery.update(project, id);
        }
    }

    @Override
    protected void newEditModel(HttpServletRequest req, HttpServletResponse resp) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException, ServletException, IOException {
        String url = req.getPathInfo();
        DbModel model = getDbModel(Integer.parseInt(req.getParameter("id")), classDbModel);
        if (!model.get("id").toString().equals("0") || url.equals("/new")) {
            req.setAttribute("model", model);
            req.setAttribute("companyList", getAllModels(serviceQueryCompany));
            req.setAttribute("customerList", getAllModels(serviceQueryCustomer));
            req.getRequestDispatcher("/jsp/" + jspEdit).forward(req, resp);
        } else {
            redirect(resp);
        }
    }
}

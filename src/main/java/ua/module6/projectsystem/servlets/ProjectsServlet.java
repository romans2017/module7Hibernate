package ua.module6.projectsystem.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.module6.projectsystem.models.*;
import ua.module6.projectsystem.queries.Query;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@WebServlet("/projects/*")
public class ProjectsServlet extends AbstractServlet {

    private Query serviceQueryCompany;
    private Query serviceQueryCustomer;
    private Query serviceQueryDeveloper;

    @Override
    public void init() {
        this.serviceQuery = (Query) getServletContext().getAttribute("projectQuery");
        this.serviceQueryCompany = (Query) getServletContext().getAttribute("companyQuery");
        this.serviceQueryCustomer = (Query) getServletContext().getAttribute("customerQuery");
        this.serviceQueryDeveloper = (Query) getServletContext().getAttribute("developerQuery");
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
    protected void removeModel(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer project_id = Integer.parseInt(req.getParameter("id"));
        serviceQuery.removeFromBindingTable("developers_projects", Map.of("project_id", project_id));
        serviceQuery.delete(project_id);
        redirect(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        try {
            switch (url) {
                case "/edit", "/new" -> newEditModel(req, resp);
                case "/remove" -> removeModel(req, resp);
                case "/save" -> saveModel(req, resp);
                case "/addDeveloper" -> addDeveloper(req, resp);
                case "/removeDeveloper" -> removeDeveloper(req, resp);
            }
        } catch (SQLException | NoSuchFieldException | IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void postEditRequest(DbModel dbModel, HttpServletRequest req, HttpServletResponse resp) throws SQLException, NoSuchFieldException, IllegalAccessException, ServletException, IOException {
        req.setAttribute("model", dbModel);
        req.setAttribute("companyList", getAllModels(serviceQueryCompany));
        req.setAttribute("customerList", getAllModels(serviceQueryCustomer));
        req.setAttribute("developerList", getAllModels(serviceQueryDeveloper));
        req.setAttribute("developersProjects", getDevelopers(dbModel));
        resp.reset();
        req.getRequestDispatcher("/jsp/" + jspEdit).forward(req, resp);
    }

    private void addDeveloper(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Integer developer_id = Integer.parseInt(req.getParameter("developer_id"));
        Integer project_id = Integer.parseInt(req.getParameter("project_id"));
        serviceQuery.addToBindingTable("developers_projects", Map.of("developer_id", developer_id, "project_id", project_id));

        DbModel project = getDbModel(project_id, Skill.class);
        postEditRequest(project, req, resp);
    }

    private void removeDeveloper(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Integer project_id = Integer.parseInt(req.getParameter("project_id"));
        serviceQuery.removeFromBindingTable("developers_projects", Map.of("developer_id", id, "project_id", project_id));

        DbModel project = getDbModel(project_id, Developer.class);
        postEditRequest(project, req, resp);
    }

    private ModelsList getDevelopers(DbModel dbModel) throws NoSuchFieldException, IllegalAccessException, SQLException {
        return serviceQuery.getFromBindingTable(serviceQueryDeveloper,
                "developers_projects",
                "developer_id",
                Map.of("project_id", (Integer) dbModel.get("id")));
    }
}
package ua.module7.hibernate.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.module7.hibernate.queries.Query;
import ua.module7.hibernate.models.DbModel;
import ua.module7.hibernate.models.Developer;
import ua.module7.hibernate.models.ModelsList;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/developers/*")
public class DevelopersServlet extends AbstractServlet {

    private Query serviceQueryCompany;
    private Query serviceQueryProject;
    private Query serviceQuerySkill;

    @Override
    public void init() {
        this.serviceQuery = (Query) getServletContext().getAttribute("developerQuery");
        this.serviceQueryCompany = (Query) getServletContext().getAttribute("companyQuery");
        this.serviceQueryProject = (Query) getServletContext().getAttribute("projectQuery");
        this.serviceQuerySkill = (Query) getServletContext().getAttribute("skillQuery");
        this.classDbModel = Developer.class;
        this.jspView = "developers.jsp";
        this.jspEdit = "developer.jsp";
        this.redirectPath = "developers";
    }

    @Override
    protected void createEditModel(HttpServletRequest req) throws NumberFormatException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Developer developer = new Developer();
        developer.setId(id);
        developer.setName(req.getParameter("name"));
        developer.setAge(Integer.parseInt(req.getParameter("age")));
        developer.setCompany_id(Integer.parseInt(req.getParameter("company_id")));
        developer.setSalary(Integer.parseInt(req.getParameter("salary")));
        if (id == 0) {
            serviceQuery.create(developer);
        } else {
            serviceQuery.update(developer, id);
        }
    }

    @Override
    protected void removeModel(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer developer_id = Integer.parseInt(req.getParameter("id"));
        serviceQuery.removeFromBindingTable("developers_projects", Map.of("developer_id", developer_id));
        serviceQuery.removeFromBindingTable("developers_skills", Map.of("developer_id", developer_id));
        serviceQuery.delete(developer_id);
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
                case "/addProject" -> addProject(req, resp);
                case "/removeProject" -> removeProject(req, resp);
                case "/addSkill" -> addSkill(req, resp);
                case "/removeSkill" -> removeSkill(req, resp);
            }
        } catch (SQLException | NoSuchFieldException | IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void postEditRequest(DbModel dbModel, HttpServletRequest req, HttpServletResponse resp) throws SQLException, NoSuchFieldException, IllegalAccessException, ServletException, IOException {
        req.setAttribute("model", dbModel);
        req.setAttribute("developersProjects", getProjects(dbModel));
        req.setAttribute("developersSkills", getSkills(dbModel));
        req.setAttribute("companyList", getAllModels(serviceQueryCompany));
        req.setAttribute("projectList", getAllModels(serviceQueryProject));
        req.setAttribute("skillList", getAllModels(serviceQuerySkill));
        resp.reset();
        req.getRequestDispatcher("/jsp/" + jspEdit).forward(req, resp);
    }

    private void addProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Integer developer_id = Integer.parseInt(req.getParameter("developer_id"));
        Integer project_id = Integer.parseInt(req.getParameter("project_id"));
        serviceQuery.addToBindingTable("developers_projects", Map.of("developer_id", developer_id, "project_id", project_id));

        DbModel developer = getDbModel(developer_id, Developer.class);
        postEditRequest(developer, req, resp);
    }

    private void addSkill(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Integer developer_id = Integer.parseInt(req.getParameter("developer_id"));
        Integer skill_id = Integer.parseInt(req.getParameter("skill_id"));
        serviceQuery.addToBindingTable("developers_skills", Map.of("developer_id", developer_id, "skill_id", skill_id));

        DbModel developer = getDbModel(developer_id, Developer.class);
        postEditRequest(developer, req, resp);
    }

    private void removeProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Integer developer_id = Integer.parseInt(req.getParameter("developer_id"));
        serviceQuery.removeFromBindingTable("developers_projects", Map.of("developer_id", developer_id, "project_id", id));

        DbModel developer = getDbModel(developer_id, Developer.class);
        postEditRequest(developer, req, resp);
    }

    private void removeSkill(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Integer developer_id = Integer.parseInt(req.getParameter("developer_id"));
        serviceQuery.removeFromBindingTable("developers_skills", Map.of("developer_id", developer_id, "skill_id", id));

        DbModel developer = getDbModel(developer_id, Developer.class);
        postEditRequest(developer, req, resp);
    }

    private ModelsList getProjects(DbModel dbModel) throws NoSuchFieldException, IllegalAccessException, SQLException {
        return serviceQuery.getFromBindingTable(serviceQueryProject,
                "developers_projects",
                "project_id",
                Map.of("developer_id", (Integer) dbModel.get("id")));
    }

    private ModelsList getSkills(DbModel dbModel) throws NoSuchFieldException, IllegalAccessException, SQLException {
        return serviceQuery.getFromBindingTable(serviceQuerySkill,
                "developers_skills",
                "skill_id",
                Map.of("developer_id", (Integer) dbModel.get("id")));
    }
}
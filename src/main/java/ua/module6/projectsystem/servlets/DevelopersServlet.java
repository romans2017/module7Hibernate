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
import java.util.Map;

@WebServlet("/developers/*")
public class DevelopersServlet extends AbstractServlet {

    private Query serviceQueryDevelopersProjects;
    private Query serviceQueryDevelopersSkills;
    private Query serviceQueryCompany;
    private Query serviceQueryProject;
    private Query serviceQuerySkill;

    @Override
    public void init() {
        this.serviceQuery = (Query) getServletContext().getAttribute("developerQuery");
        this.serviceQueryDevelopersProjects = (Query) getServletContext().getAttribute("developersProjectsQuery");
        this.serviceQueryDevelopersSkills = (Query) getServletContext().getAttribute("developersSkillsQuery");
        this.serviceQueryCompany = (Query) getServletContext().getAttribute("companyQuery");
        this.serviceQueryProject = (Query) getServletContext().getAttribute("projectQuery");
        this.serviceQuerySkill = (Query) getServletContext().getAttribute("skillQuery");
        this.classDbModel = Developer.class;
        this.jspView = "developers.jsp";
        this.jspEdit = "developer.jsp";
        this.redirectPath = "developers";
    }

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

    private ModelsList getAdditionalRefs(DbModel model, Query sQuery) throws NoSuchFieldException, IllegalAccessException, SQLException {
        return sQuery.get(Map.of("developer_id", model.get("id")));
    }

    private void postEditRequest(DbModel dbModel, HttpServletRequest req, HttpServletResponse resp) throws SQLException, NoSuchFieldException, IllegalAccessException, ServletException, IOException {
        req.setAttribute("model", dbModel);
        req.setAttribute("developersProjects", getAdditionalRefs(dbModel, serviceQueryDevelopersProjects));
        req.setAttribute("developersSkills", getAdditionalRefs(dbModel, serviceQueryDevelopersSkills));
        req.setAttribute("companyList", getAllModels(serviceQueryCompany));
        req.setAttribute("projectList", getAllModels(serviceQueryProject));
        req.setAttribute("skillList", getAllModels(serviceQuerySkill));
        resp.reset();
        req.getRequestDispatcher("/jsp/" + jspEdit).forward(req, resp);
    }

    private void addProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Integer developer_id = Integer.parseInt(req.getParameter("developer_id"));
        Integer project_id = Integer.parseInt(req.getParameter("project_id"));
        DevelopersProjects developersProjects = new DevelopersProjects();
        developersProjects.setDeveloper_id(developer_id);
        developersProjects.setProject_id(project_id);
        serviceQueryDevelopersProjects.create(developersProjects);

        DbModel developer = getDbModel(developer_id, Developer.class);
        postEditRequest(developer, req, resp);
    }

    private void addSkill(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Integer developer_id = Integer.parseInt(req.getParameter("developer_id"));
        Integer skill_id = Integer.parseInt(req.getParameter("skill_id"));
        DevelopersSkills developersSkills = new DevelopersSkills();
        developersSkills.setDeveloper_id(developer_id);
        developersSkills.setSkill_id(skill_id);
        serviceQueryDevelopersSkills.create(developersSkills);

        DbModel developer = getDbModel(developer_id, Developer.class);
        postEditRequest(developer, req, resp);
    }

    private void removeAdditionalRef(Query sQuery, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Integer developer_id = Integer.parseInt(req.getParameter("developer_id"));
        sQuery.delete(developer_id, id);

        DbModel developer = getDbModel(developer_id, Developer.class);
        postEditRequest(developer, req, resp);
    }

    @Override
    protected void newEditModel(HttpServletRequest req, HttpServletResponse resp) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException, ServletException, IOException {
        String url = req.getPathInfo();
        DbModel model = getDbModel(Integer.parseInt(req.getParameter("id")), classDbModel);
        if (!model.get("id").toString().equals("0") || url.equals("/new")) {
            postEditRequest(model, req, resp);
        } else {
            redirect(resp);
        }
    }

    @Override
    protected void removeModel(HttpServletRequest req, HttpServletResponse resp) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException, IOException {
        DbModel model = getDbModel(Integer.parseInt(req.getParameter("id")), classDbModel);
        getAdditionalRefs(model, serviceQueryDevelopersProjects)
                .forEach(item -> {
                    try {
                        serviceQueryDevelopersProjects.delete((Integer) item.get("developer_id"), (Integer) item.get("project_id"));
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
        getAdditionalRefs(model, serviceQueryDevelopersSkills).forEach(item -> {
            try {
                serviceQueryDevelopersSkills.delete((Integer) item.get("developer_id"), (Integer) item.get("skill_id"));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        serviceQuery.delete((Integer) model.get("id"));
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
                case "/removeProject" -> removeAdditionalRef(serviceQueryDevelopersProjects, req, resp);
                case "/addSkill" -> addSkill(req, resp);
                case "/removeSkill" -> removeAdditionalRef(serviceQueryDevelopersSkills, req, resp);
            }
        } catch (SQLException | NoSuchFieldException | IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException throwables) {
            throwables.printStackTrace();
        }
    }
}
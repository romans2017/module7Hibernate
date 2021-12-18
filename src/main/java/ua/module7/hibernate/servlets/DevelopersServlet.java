package ua.module7.hibernate.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.module7.hibernate.dao.Dao;
import ua.module7.hibernate.pojo.Company;
import ua.module7.hibernate.pojo.Developer;
import ua.module7.hibernate.pojo.Project;
import ua.module7.hibernate.pojo.Skill;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@WebServlet("/developers/*")
public class DevelopersServlet extends AbstractServlet<Developer> {

    private Dao<Project> serviceDaoProject;
    private Dao<Skill> serviceDaoSkill;
    private Dao<Company> serviceDaoCompany;

    @SuppressWarnings("unchecked")
    @Override
    public void init() throws ServletException {
        super.init();
        this.serviceDao = (Dao<Developer>) getServletContext().getAttribute("developerDao");
        this.serviceDaoProject = (Dao<Project>) getServletContext().getAttribute("projectDao");
        this.serviceDaoSkill = (Dao<Skill>) getServletContext().getAttribute("skillDao");
        this.serviceDaoCompany = (Dao<Company>) getServletContext().getAttribute("companyDao");
        this.jspView = "developers.jsp";
        this.jspEdit = "developer.jsp";
        this.redirectPath = "developers";
    }

    @Override
    protected void createUpdateModel(HttpServletRequest req) throws NumberFormatException {
        int id = Integer.parseInt(req.getParameter("id"));

        if (id == 0) {
            Developer developer = new Developer()
                    .setName(req.getParameter("name"))
                    .setSalary(Integer.parseInt(req.getParameter("salary")))
                    .setAge(Integer.parseInt(req.getParameter("age")));
            serviceDao.create(developer);
            Company company = serviceDaoCompany.read(Integer.parseInt(req.getParameter("company_id")));
            if (company != null) {
                developer.addCompany(company);
                serviceDao.update(developer);
            }
        } else {
            Developer developer = serviceDao.read(id);
            if (developer != null) {
                developer.setName(req.getParameter("name"))
                        .setSalary(Integer.parseInt(req.getParameter("salary")))
                        .setAge(Integer.parseInt(req.getParameter("age")));
                Company company = serviceDaoCompany.read(Integer.parseInt(req.getParameter("company_id")));
                if (company != null) {
                    developer.addCompany(company);
                } else {
                    if (developer.getCompany() != null) {
                        developer.removeCompany(developer.getCompany());
                    }
                }
                serviceDao.update(developer);
            }
        }
    }

    @Override
    protected void postEditRequest(Developer model, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("model", model);
        req.setAttribute("projectList", ServletService.getAllModels(serviceDaoProject, Project.class));
        req.setAttribute("skillList", ServletService.getAllModels(serviceDaoSkill, Skill.class));
        req.setAttribute("companyList", ServletService.getAllModels(serviceDaoCompany, Company.class));
        resp.reset();
        req.getRequestDispatcher("/jsp/" + jspEdit).forward(req, resp);
    }

    private void addProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Developer developer = serviceDao.read(Integer.parseInt(req.getParameter("developer_id")));
        Project project = serviceDaoProject.read(Integer.parseInt(req.getParameter("project_id")));
        developer = addRemoveBindFromOwnerToMapped(developer, project, developer::addProject);
        postEditRequest(developer, req, resp);
    }

    private void removeProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Developer developer = serviceDao.read(Integer.parseInt(req.getParameter("developer_id")));
        Project project = serviceDaoProject.read(Integer.parseInt(req.getParameter("project_id")));
        developer = addRemoveBindFromOwnerToMapped(developer, project, developer::removeProject);
        postEditRequest(developer, req, resp);
    }

    private void addSkill(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Developer developer = serviceDao.read(Integer.parseInt(req.getParameter("developer_id")));
        Skill skill = serviceDaoSkill.read(Integer.parseInt(req.getParameter("skill_id")));
        developer = addRemoveBindFromOwnerToMapped(developer, skill, developer::addSkill);
        postEditRequest(developer, req, resp);
    }

    private void removeSkill(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Developer developer = serviceDao.read(Integer.parseInt(req.getParameter("developer_id")));
        Skill skill = serviceDaoSkill.read(Integer.parseInt(req.getParameter("skill_id")));
        developer = addRemoveBindFromOwnerToMapped(developer, skill, developer::removeSkill);
        postEditRequest(developer, req, resp);
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
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException throwables) {
            throwables.printStackTrace();
        }
    }
}
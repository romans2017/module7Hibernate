package ua.module7.hibernate.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.module7.hibernate.dao.Dao;
import ua.module7.hibernate.pojo.Company;
import ua.module7.hibernate.pojo.Developer;
import ua.module7.hibernate.pojo.Project;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/companies/*")
public class CompaniesServlet extends AbstractServlet<Company> {

    private Dao<Project> serviceDaoProject;
    private Dao<Developer> serviceDaoDeveloper;

    @SuppressWarnings("unchecked")
    @Override
    public void init() throws ServletException {
        super.init();
        this.serviceDao = (Dao<Company>) getServletContext().getAttribute("companyDao");
        this.serviceDaoProject = (Dao<Project>) getServletContext().getAttribute("projectDao");
        this.serviceDaoDeveloper = (Dao<Developer>) getServletContext().getAttribute("developerDao");
        this.jspView = "companies.jsp";
        this.jspEdit = "company.jsp";
        this.redirectPath = "companies";
    }

    @Override
    protected Company createUpdateModel(HttpServletRequest req) throws NumberFormatException {
        int id;
        Company company;
        if (req.getParameter("id") == null) {
            if (req.getParameter("company_id") == null) {
                id = 0;
            } else {
                id = Integer.parseInt(req.getParameter("company_id"));
            }
        } else {
            id = Integer.parseInt(req.getParameter("id"));
        }
        if (id == 0) {
            company = new Company()
                    .setName(req.getParameter("name"))
                    .setCountry(req.getParameter("country"));
            serviceDao.create(company);
        } else {
            company = serviceDao.read(id);
            if (company != null) {
                company.setCountry(req.getParameter("country"))
                        .setName(req.getParameter("name"));
                serviceDao.update(company);
            }
        }
        return company;
    }

    @Override
    protected void postEditRequest(Company model, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("model", model);
        req.setAttribute("projectList", ServletService.getAllModels(serviceDaoProject, Project.class));
        req.setAttribute("developerList", ServletService.getAllModels(serviceDaoDeveloper, Developer.class));
        resp.reset();
        req.getRequestDispatcher("/jsp/" + jspEdit).forward(req, resp);
    }

    private void addProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Company company = createUpdateModel(req);
        Project project = serviceDaoProject.read(Integer.parseInt(req.getParameter("project_id")));
        company = addRemoveBindFromMappedToOwner(project, company, serviceDaoProject, project::addCompany);
        postEditRequest(company, req, resp);
    }

    private void removeProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Company company = serviceDao.read(Integer.parseInt(req.getParameter("company_id")));
        Project project = serviceDaoProject.read(Integer.parseInt(req.getParameter("project_id")));
        company = addRemoveBindFromMappedToOwner(project, company, serviceDaoProject, project::removeCompany);
        postEditRequest(company, req, resp);
    }

    private void addDeveloper(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Company company = createUpdateModel(req);
        Developer developer = serviceDaoDeveloper.read(Integer.parseInt(req.getParameter("developer_id")));
        company = addRemoveBindFromMappedToOwner(developer, company, serviceDaoDeveloper, developer::addCompany);
        postEditRequest(company, req, resp);
    }

    private void removeDeveloper(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Company company = serviceDao.read(Integer.parseInt(req.getParameter("company_id")));
        Developer developer = serviceDaoDeveloper.read(Integer.parseInt(req.getParameter("developer_id")));
        company = addRemoveBindFromMappedToOwner(developer, company, serviceDaoDeveloper, developer::removeCompany);
        postEditRequest(company, req, resp);
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
                case "/addDeveloper" -> addDeveloper(req, resp);
                case "/removeDeveloper" -> removeDeveloper(req, resp);
            }
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException throwables) {
            throwables.printStackTrace();
        }
    }
}

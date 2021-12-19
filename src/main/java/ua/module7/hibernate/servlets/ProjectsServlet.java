package ua.module7.hibernate.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.module7.hibernate.dao.Dao;
import ua.module7.hibernate.pojo.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@WebServlet("/projects/*")
public class ProjectsServlet extends AbstractServlet<Project> {

    private Dao<Customer> serviceDaoCustomer;
    private Dao<Developer> serviceDaoDeveloper;
    private Dao<Company> serviceDaoCompany;

    @SuppressWarnings("unchecked")
    @Override
    public void init() throws ServletException {
        super.init();
        this.serviceDao = (Dao<Project>) getServletContext().getAttribute("projectDao");
        this.serviceDaoDeveloper = (Dao<Developer>) getServletContext().getAttribute("developerDao");
        this.serviceDaoCustomer = (Dao<Customer>) getServletContext().getAttribute("customerDao");
        this.serviceDaoCompany = (Dao<Company>) getServletContext().getAttribute("companyDao");
        this.jspView = "projects.jsp";
        this.jspEdit = "project.jsp";
        this.redirectPath = "projects";
    }

    @Override
    protected void createUpdateModel(HttpServletRequest req) throws NumberFormatException {
        int id = Integer.parseInt(req.getParameter("id"));

        LocalDate docDate = null;
        try {
            docDate = LocalDate.parse(req.getParameter("creation_date"));
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }

        if (id == 0) {
            Project project = new Project()
                    .setName(req.getParameter("name"))
                    .setCost(Integer.parseInt(req.getParameter("cost")))
                    .setCreationDate(docDate)
                    .setDescription(req.getParameter("name"));
            serviceDao.create(project);
            Company company = serviceDaoCompany.read(Integer.parseInt(req.getParameter("company_id")));
            if (company != null) {
                project.addCompany(company);
            }
            Customer customer = serviceDaoCustomer.read(Integer.parseInt(req.getParameter("customer_id")));
            if (customer != null) {
                project.addCustomer(customer);
            }
            if (customer != null || company != null) {
                serviceDao.update(project);
            }
        } else {
            Project project = serviceDao.read(id);
            if (project != null) {
                project.setName(req.getParameter("name"))
                        .setCost(Integer.parseInt(req.getParameter("cost")))
                        .setCreationDate(docDate)
                        .setDescription(req.getParameter("name"));
                Company company = serviceDaoCompany.read(Integer.parseInt(req.getParameter("company_id")));
                if (company != null) {
                    project.addCompany(company);
                } else {
                    if (project.getCompany() != null) {
                        project.removeCompany(project.getCompany());
                    }
                }
                Customer customer = serviceDaoCustomer.read(Integer.parseInt(req.getParameter("customer_id")));
                if (customer != null) {
                    project.addCustomer(customer);
                } else {
                    if (project.getCustomer() != null) {
                        project.removeCustomer(project.getCustomer());
                    }
                }
                if (customer != null || company != null) {
                    serviceDao.update(project);
                }
            }
        }
    }

    @Override
    protected void postEditRequest(Project model, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("model", model);
        req.setAttribute("developerList", ServletService.getAllModels(serviceDaoDeveloper, Developer.class));
        req.setAttribute("customerList", ServletService.getAllModels(serviceDaoCustomer, Customer.class));
        req.setAttribute("companyList", ServletService.getAllModels(serviceDaoCompany, Company.class));
        resp.reset();
        req.getRequestDispatcher("/jsp/" + jspEdit).forward(req, resp);
    }

    private void addDeveloper(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Project project = serviceDao.read(Integer.parseInt(req.getParameter("project_id")));
        Developer developer = serviceDaoDeveloper.read(Integer.parseInt(req.getParameter("developer_id")));
        project = addRemoveBindFromMappedToOwner(developer, project, serviceDaoDeveloper, developer::addProject);
        postEditRequest(project, req, resp);
    }

    private void removeDeveloper(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Project project = serviceDao.read(Integer.parseInt(req.getParameter("project_id")));
        Developer developer = serviceDaoDeveloper.read(Integer.parseInt(req.getParameter("developer_id")));
        project = addRemoveBindFromMappedToOwner(developer, project, serviceDaoDeveloper, developer::removeProject);
        postEditRequest(project, req, resp);
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
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException throwables) {
            throwables.printStackTrace();
        }
    }
}
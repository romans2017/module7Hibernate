package ua.module7.hibernate.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.module7.hibernate.dao.Dao;
import ua.module7.hibernate.pojo.Customer;
import ua.module7.hibernate.pojo.Project;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/customers/*")
public class CustomersServlet extends AbstractServlet<Customer> {

    private Dao<Project> serviceDaoProject;

    @SuppressWarnings("unchecked")
    @Override
    public void init() throws ServletException {
        super.init();
        this.serviceDao = (Dao<Customer>) getServletContext().getAttribute("customerDao");
        this.serviceDaoProject = (Dao<Project>) getServletContext().getAttribute("projectDao");
        this.jspView = "customers.jsp";
        this.jspEdit = "customer.jsp";
        this.redirectPath = "customers";
    }

    @Override
    protected void createUpdateModel(HttpServletRequest req) throws NumberFormatException {
        int id = Integer.parseInt(req.getParameter("id"));

        if (id == 0) {
            Customer customer = new Customer()
                    .setName(req.getParameter("name"))
                    .setCountry(req.getParameter("country"));
            serviceDao.create(customer);
        } else {
            Customer customer = serviceDao.read(id);
            if (customer != null) {
                customer.setCountry(req.getParameter("country"))
                        .setName(req.getParameter("name"));
                serviceDao.update(customer);
            }
        }
    }

    @Override
    protected void postEditRequest(Customer model, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("model", model);
        req.setAttribute("projectList", ServletService.getAllModels(serviceDaoProject, Project.class));
        resp.reset();
        req.getRequestDispatcher("/jsp/" + jspEdit).forward(req, resp);
    }

    private void addProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Customer customer = serviceDao.read(Integer.parseInt(req.getParameter("customer_id")));
        Project project = serviceDaoProject.read(Integer.parseInt(req.getParameter("project_id")));
        customer = addRemoveBindFromMappedToOwner(project, customer, serviceDaoProject, project::addCustomer);
        postEditRequest(customer, req, resp);
    }

    private void removeProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Customer customer = serviceDao.read(Integer.parseInt(req.getParameter("customer_id")));
        Project project = serviceDaoProject.read(Integer.parseInt(req.getParameter("project_id")));
        customer = addRemoveBindFromMappedToOwner(project, customer, serviceDaoProject, project::removeCustomer);
        postEditRequest(customer, req, resp);
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
            }
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException throwables) {
            throwables.printStackTrace();
        }
    }
}

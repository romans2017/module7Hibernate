package ua.module7.hibernate.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import ua.module7.hibernate.models.Customer;
import ua.module7.hibernate.queries.Query;

@WebServlet("/customers/*")
public class CustomersServlet extends AbstractServlet {

    @Override
    public void init() {
        this.serviceQuery = (Query) getServletContext().getAttribute("customerQuery");
        this.classDbModel = Customer.class;
        this.jspView = "customers.jsp";
        this.jspEdit = "customer.jsp";
        this.redirectPath = "customers";
    }

    protected void createEditModel(HttpServletRequest req) throws NumberFormatException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Customer company = new Customer();
        company.setId(id);
        company.setName(req.getParameter("name"));
        company.setCountry(req.getParameter("country"));
        if (id == 0) {
            serviceQuery.create(company);
        } else {
            serviceQuery.update(company, id);
        }
    }
}

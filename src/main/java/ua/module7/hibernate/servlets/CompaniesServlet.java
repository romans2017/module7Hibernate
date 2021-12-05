package ua.module7.hibernate.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import ua.module7.hibernate.models.Company;
import ua.module7.hibernate.queries.Query;

@WebServlet("/companies/*")
public class CompaniesServlet extends AbstractServlet {

    @Override
    public void init() {
        this.serviceQuery = (Query) getServletContext().getAttribute("companyQuery");
        this.classDbModel = Company.class;
        this.jspView = "companies.jsp";
        this.jspEdit = "company.jsp";
        this.redirectPath = "companies";
    }

    protected void createEditModel(HttpServletRequest req) throws NumberFormatException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Company company = new Company();
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

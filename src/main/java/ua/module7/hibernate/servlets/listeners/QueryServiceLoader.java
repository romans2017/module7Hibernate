package ua.module7.hibernate.servlets.listeners;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ua.module7.hibernate.connectors.PersistanceConnecter;
import ua.module7.hibernate.dao.*;

@WebListener
public class QueryServiceLoader implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        PersistanceConnecter.getEntityManager();

        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("companyDao", CompanyDao.getInstance());
        servletContext.setAttribute("customerDao", CustomerDao.getInstance());
        servletContext.setAttribute("developerDao", DeveloperDao.getInstance());
        servletContext.setAttribute("projectDao", ProjectDao.getInstance());
        servletContext.setAttribute("skillDao", SkillDao.getInstance());
    }
}

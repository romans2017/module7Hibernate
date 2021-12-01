package ua.module6.projectsystem.servlets.listeners;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ua.module6.projectsystem.connectors.ConnectorManager;
import ua.module6.projectsystem.connectors.DbType;
import ua.module6.projectsystem.connectors.dbcontrollers.DbConnector;
import ua.module6.projectsystem.queries.*;

@WebListener
public class QueryServiceLoader implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DbConnector dbConnector = ConnectorManager.connectTo(DbType.POSTGRES);

        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("companyQuery", CompanyQuery.getInstance(dbConnector));
        servletContext.setAttribute("customerQuery", CustomerQuery.getInstance(dbConnector));
        servletContext.setAttribute("developerQuery", DeveloperQuery.getInstance(dbConnector));
        servletContext.setAttribute("developersProjectsQuery", DevelopersProjectsQuery.getInstance(dbConnector));
        servletContext.setAttribute("developersSkillsQuery", DevelopersSkillsQuery.getInstance(dbConnector));
        servletContext.setAttribute("projectQuery", ProjectQuery.getInstance(dbConnector));
        servletContext.setAttribute("skillQuery", SkillQuery.getInstance(dbConnector));
        servletContext.setAttribute("reportDevelopersJavaQuery", ReportDevelopersJavaQuery.getInstance(dbConnector));
        servletContext.setAttribute("reportDevelopersMiddleQuery", ReportDevelopersMiddleQuery.getInstance(dbConnector));
        servletContext.setAttribute("reportDevelopersProjectsQuery", ReportDevelopersProjectsQuery.getInstance(dbConnector));
        servletContext.setAttribute("reportDevelopersSalaryQuery", ReportDevelopersSalaryQuery.getInstance(dbConnector));
        servletContext.setAttribute("reportProjectsQuery", ReportProjectsQuery.getInstance(dbConnector));

    }
}

package ua.module4.projectsystem.queries;

import ua.module4.projectsystem.connectors.dbcontrollers.DbConnector;
import ua.module4.projectsystem.models.DbModel;
import ua.module4.projectsystem.models.ModelsList;
import ua.module4.projectsystem.models.ReportDevelopersProjects;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportDevelopersProjectsQuery extends AbstractQuery  {

    private static ReportDevelopersProjectsQuery instance;

    private ReportDevelopersProjectsQuery(DbConnector dbConnector) {
        super(dbConnector);
    }

    public static ReportDevelopersProjectsQuery getInstance(DbConnector dbConnector) {
        if (instance == null) {
            instance = new ReportDevelopersProjectsQuery(dbConnector);
        }
        return instance;
    }

    @Override
    protected String getTableName() {
        return "developers_projects";
    }

    @Override
    protected Class<? extends DbModel> getTableClass() {
        return ReportDevelopersProjects.class;
    }

    @Override
    protected ModelsList normalizeSqlResponse(ResultSet resultSet) throws SQLException {

        ModelsList list = new ModelsList();
        while (resultSet.next()) {
            ReportDevelopersProjects reportDevelopersProjects = new ReportDevelopersProjects();
            reportDevelopersProjects.setProject_id(resultSet.getInt("project_id"));
            reportDevelopersProjects.setProject_name(resultSet.getString("project_name"));
            reportDevelopersProjects.setDeveloper_id(resultSet.getInt("developer_id"));
            reportDevelopersProjects.setDeveloper_name(resultSet.getString("developer_name"));
            reportDevelopersProjects.setAge(resultSet.getInt("age"));
            reportDevelopersProjects.setSalary(resultSet.getInt("salary"));
            list.add(reportDevelopersProjects);
        }
        resultSet.close();
        return list;
    }

    public StringBuilder getAdvancedMainRequest() {

        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder
                .append("SELECT " +
                        "developers.name as developer_name, " +
                        "developers.salary as salary, " +
                        "developers.age as age, " +
                        "developers.id as developer_id, " +
                        "projects.name as project_name, " +
                        "projects.id as project_id")
                .append(" FROM ")
                .append(getTableName())
                .append(" JOIN developers ON ")
                .append(getTableName())
                .append(".developer_id = developers.id")
                .append(" JOIN projects ON ")
                .append(getTableName())
                .append(".project_id = projects.id");

    }
}

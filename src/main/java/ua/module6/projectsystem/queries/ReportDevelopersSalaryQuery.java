package ua.module6.projectsystem.queries;

import ua.module6.projectsystem.connectors.dbcontrollers.DbConnector;
import ua.module6.projectsystem.models.DbModel;
import ua.module6.projectsystem.models.ModelsList;
import ua.module6.projectsystem.models.ReportDevelopersSalary;


import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportDevelopersSalaryQuery extends AbstractQuery  {

    private static ReportDevelopersSalaryQuery instance;

    private ReportDevelopersSalaryQuery(DbConnector dbConnector) {
        super(dbConnector);
    }

    public static ReportDevelopersSalaryQuery getInstance(DbConnector dbConnector) {
        if (instance == null) {
            instance = new ReportDevelopersSalaryQuery(dbConnector);
        }
        return instance;
    }

    @Override
    public String getTableName() {
        return "developers_projects";
    }

    @Override
    protected Class<? extends DbModel> getTableClass() {
        return ReportDevelopersSalary.class;
    }

    @Override
    public ModelsList normalizeSqlResponse(ResultSet resultSet) throws SQLException {

        ModelsList list = new ModelsList();
        while (resultSet.next()) {
            ReportDevelopersSalary reportDevelopersSalary = new ReportDevelopersSalary();
            reportDevelopersSalary.setProject_id(resultSet.getInt("project_id"));
            reportDevelopersSalary.setProject_name(resultSet.getString("project_name"));
            reportDevelopersSalary.setDeveloper_name(resultSet.getString("developer_name"));
            reportDevelopersSalary.setSalary(resultSet.getInt("salary"));
            list.add(reportDevelopersSalary);
        }
        resultSet.close();
        return list;
    }

    public StringBuilder getAdvancedMainRequest() {

        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append("SELECT " +
                        "developers.name as developer_name, " +
                        "developers.salary as salary, " +
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

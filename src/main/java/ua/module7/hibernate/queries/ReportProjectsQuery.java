package ua.module7.hibernate.queries;

import ua.module7.hibernate.connectors.dbcontrollers.DbConnector;
import ua.module7.hibernate.models.DbModel;
import ua.module7.hibernate.models.ModelsList;
import ua.module7.hibernate.models.ReportProjects;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportProjectsQuery extends AbstractQuery  {

    private static ReportProjectsQuery instance;

    private ReportProjectsQuery(DbConnector dbConnector) {
        super(dbConnector);
    }

    public static ReportProjectsQuery getInstance(DbConnector dbConnector) {
        if (instance == null) {
            instance = new ReportProjectsQuery(dbConnector);
        }
        return instance;
    }

    @Override
    public String getTableName() {
        return "developers_projects";
    }

    @Override
    protected Class<? extends DbModel> getTableClass() {
        return ReportProjects.class;
    }

    @Override
    public ModelsList normalizeSqlResponse(ResultSet resultSet) throws SQLException {

        ModelsList list = new ModelsList();
        while (resultSet.next()) {
            ReportProjects reportProjects = new ReportProjects();
            reportProjects.setProject_id(resultSet.getInt("project_id"));
            reportProjects.setProject_name(resultSet.getString("project_name"));
            reportProjects.setCreation_date(resultSet.getDate("creation_date"));
            reportProjects.setNumber_developers(resultSet.getInt("number_developers"));
            list.add(reportProjects);
        }
        resultSet.close();
        return list;
    }

    public StringBuilder getAdvancedMainRequest() {

        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder
                .append("""
                        select
                            projects.id as project_id,
                            projects.name as project_name,
                            projects.creation_date as creation_date,
                            nested.number_developers
                        from projects
                        join
                            (select
                                project_id as project_id,
                                count(distinct developer_id) as number_developers
                                from developers_projects
                                group by project_id) as nested on projects.id = nested.project_id""");

    }
}

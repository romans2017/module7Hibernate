package ua.module4.projectsystem.queries;

import ua.module4.projectsystem.connectors.dbcontrollers.DbConnector;
import ua.module4.projectsystem.models.DbModel;
import ua.module4.projectsystem.models.DevelopersProjects;
import ua.module4.projectsystem.models.ModelsList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DevelopersProjectsQuery extends AbstractQuery {

    private static DevelopersProjectsQuery instance;

    private DevelopersProjectsQuery(DbConnector dbConnector) {
        super(dbConnector);
    }

    public static DevelopersProjectsQuery getInstance(DbConnector dbConnector) {
        if (instance == null) {
            instance = new DevelopersProjectsQuery(dbConnector);
        }
        return instance;
    }

    @Override
    protected String getTableName() {
        return "developers_projects";
    }

    @Override
    protected Class<? extends DbModel> getTableClass() {
        return DevelopersProjects.class;
    }

    @Override
    protected ModelsList normalizeSqlResponse(ResultSet resultSet) throws SQLException {
        ModelsList list = new ModelsList();

        while (resultSet.next()) {
            DevelopersProjects developersProjects = new DevelopersProjects();
            developersProjects.setId(resultSet.getInt("id"));
            developersProjects.setProject_id(resultSet.getInt("project_id"));
            developersProjects.setDeveloper_id(resultSet.getInt("developer_id"));
            try {
                developersProjects.setDeveloper_name(resultSet.getString("developer_name"));
                developersProjects.setProject_name(resultSet.getString("project_name"));
            } catch (SQLException ignored) {
            }
            list.add(developersProjects);
        }
        resultSet.close();
        return list;
    }

    @Override
    public StringBuilder getAdvancedMainRequest() {

        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append("SELECT ")
                .append(getTableName())
                .append(".*, ")
                .append("developers.name as developer_name, projects.name as project_name")
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

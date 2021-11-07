package ua.goit.module4.queries;

import ua.goit.module4.connectors.dbcontrollers.DbConnector;
import ua.goit.module4.models.DbModel;
import ua.goit.module4.models.DevelopersProjects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    protected List<? extends DbModel> normalizeSqlResponse(ResultSet resultSet) throws SQLException {
        List<DevelopersProjects> list = new ArrayList<>();

        while (resultSet.next()) {
            DevelopersProjects developersProjects = new DevelopersProjects();
            developersProjects.setId(resultSet.getInt("id"));
            developersProjects.setProject_id(resultSet.getInt("project_id"));
            developersProjects.setDeveloper_id(resultSet.getInt("company_id"));
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
                .append("companies.name as company_name, projects.name as project_name")
                .append(" FROM ")
                .append(getTableName())
                .append(" JOIN companies ON ")
                .append(getTableName())
                .append(".company_id = companies.id")
                .append(" JOIN projects ON ")
                .append(getTableName())
                .append(".project_id = projects.id");

    }
}

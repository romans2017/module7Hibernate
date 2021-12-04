package ua.module6.projectsystem.queries;

import ua.module6.projectsystem.connectors.dbcontrollers.DbConnector;
import ua.module6.projectsystem.models.DbModel;
import ua.module6.projectsystem.models.Developer;
import ua.module6.projectsystem.models.ModelsList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class DeveloperQuery extends AbstractQuery {
    private static DeveloperQuery instance;

    private DeveloperQuery(DbConnector dbConnector) {
        super(dbConnector);
    }

    public static DeveloperQuery getInstance(DbConnector dbConnector) {
        if (instance == null) {
            instance = new DeveloperQuery(dbConnector);
        }
        return instance;
    }

    @Override
    public String getTableName() {
        return "developers";
    }

    @Override
    protected Class<? extends DbModel> getTableClass() {
        return Developer.class;
    }

    @Override
    public ModelsList normalizeSqlResponse(ResultSet resultSet) throws SQLException {
        ModelsList list = new ModelsList();

        while (resultSet.next()) {
            Developer developer = new Developer();
            developer.setId(resultSet.getInt("id"));
            developer.setName(resultSet.getString("name"));
            developer.setCompany_id(resultSet.getInt("company_id"));
            developer.setAge(resultSet.getInt("age"));
            developer.setSalary(resultSet.getInt("salary"));
            try {
                developer.setCompany_name(resultSet.getString("company_name"));
            } catch (SQLException ignored) {
            }
            list.add(developer);
        }
        resultSet.close();
        return list;
    }

    public int addToProjects(Map<String, Integer> mapPrimaryKeys) {
        return super.addToBindingTable("developers_projects", mapPrimaryKeys);
    }

    public int removeFromProjects(Map<String, Integer> mapPrimaryKeys) {
        return super.removeFromBindingTable("developers_projects", mapPrimaryKeys);
    }

    public ModelsList getFromProjects(Map<String, Integer> mapPrimaryKeys) throws SQLException {
        return super.getFromBindingTable(ProjectQuery.getInstance(dbConnector),"developers_projects", "project_id", mapPrimaryKeys);
    }

    @Override
    public StringBuilder getAdvancedMainRequest() {

        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append("SELECT ")
                .append(getTableName())
                .append(".*, ")
                .append("companies.name as company_name")
                .append(" FROM ")
                .append(getTableName())
                .append(" JOIN companies ON ")
                .append(getTableName())
                .append(".company_id = companies.id");
    }

}

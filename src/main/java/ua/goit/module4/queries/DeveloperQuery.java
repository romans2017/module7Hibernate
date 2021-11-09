package ua.goit.module4.queries;

import ua.goit.module4.connectors.dbcontrollers.DbConnector;
import ua.goit.module4.models.DbModel;
import ua.goit.module4.models.Developer;
import ua.goit.module4.models.ModelsList;

import java.sql.ResultSet;
import java.sql.SQLException;

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
    protected String getTableName() {
        return "developers";
    }

    @Override
    protected Class<? extends DbModel> getTableClass() {
        return Developer.class;
    }

    @Override
    protected ModelsList normalizeSqlResponse(ResultSet resultSet) throws SQLException {
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

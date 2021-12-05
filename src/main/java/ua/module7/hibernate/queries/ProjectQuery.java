package ua.module7.hibernate.queries;

import ua.module7.hibernate.connectors.dbcontrollers.DbConnector;
import ua.module7.hibernate.models.DbModel;
import ua.module7.hibernate.models.ModelsList;
import ua.module7.hibernate.models.Project;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectQuery extends AbstractQuery {

    private static ProjectQuery instance;

    private ProjectQuery(DbConnector dbConnector) {
        super(dbConnector);
    }

    public static ProjectQuery getInstance(DbConnector dbConnector) {
        if (instance == null) {
            instance = new ProjectQuery(dbConnector);
        }
        return instance;
    }

    @Override
    public String getTableName() {
        return "projects";
    }

    @Override
    protected Class<? extends DbModel> getTableClass() {
        return Project.class;
    }

    @Override
    public ModelsList normalizeSqlResponse(ResultSet resultSet) throws SQLException {
        ModelsList list = new ModelsList();

        while (resultSet.next()) {
            Project project = new Project();
            project.setId(resultSet.getInt("id"));
            project.setName(resultSet.getString("name"));
            project.setCompany_id(resultSet.getInt("company_id"));
            project.setCustomer_id(resultSet.getInt("customer_id"));
            project.setDescription(resultSet.getString("description"));
            project.setCreation_date(resultSet.getDate("creation_date"));
            project.setCost(resultSet.getInt("cost"));
            try {
                project.setCompany_name(resultSet.getString("company_name"));
                project.setCustomer_name(resultSet.getString("customer_name"));
            } catch (SQLException ignored) {
            }
            list.add(project);
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
                .append("companies.name as company_name, customers.name as customer_name")
                .append(" FROM ")
                .append(getTableName())
                .append(" JOIN companies ON ")
                .append(getTableName())
                .append(".company_id = companies.id")
                .append(" JOIN customers ON ")
                .append(getTableName())
                .append(".customer_id = customers.id");

    }
}

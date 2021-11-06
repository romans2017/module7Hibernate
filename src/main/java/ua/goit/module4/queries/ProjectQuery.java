package ua.goit.module4.queries;

import ua.goit.module4.connectors.dbcontrollers.DbConnector;
import ua.goit.module4.models.DbModel;
import ua.goit.module4.models.Project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectQuery extends AbstractQuery{

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
    protected String getTableName() {
        return "projects";
    }

    @Override
    protected Class<? extends DbModel> getTableClass() {
        return Project.class;
    }

    @Override
    public List<Project> get(Map<String, Object> simpleFilter) {
        ResultSet resultSet = read(simpleFilter);
        List<Project> list = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setCompany_id(resultSet.getInt("company_id"));
                project.setCustomer_id(resultSet.getInt("customer_id"));
                project.setDescription(resultSet.getString("description"));
                project.setCreation_date(resultSet.getDate("creation_date"));
                list.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Project> getAll() {
        return get(new HashMap<>());
    }

}

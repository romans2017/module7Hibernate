package ua.goit.module4.queries;

import ua.goit.module4.connectors.dbcontrollers.DbConnector;
import ua.goit.module4.models.DbModel;
import ua.goit.module4.models.Developer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeveloperQuery extends AbstractQuery{
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
    public List<Developer> get(Map<String, Object> simpleFilter) {
        ResultSet resultSet = read(simpleFilter);
        List<Developer> list = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Developer developer = new Developer();
                developer.setId(resultSet.getInt("id"));
                developer.setName(resultSet.getString("name"));
                developer.setCompany_id(resultSet.getInt("company_id"));
                developer.setAge(resultSet.getInt("age"));
                developer.setSalary(resultSet.getInt("salary"));
                list.add(developer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Developer> getAll() {
        return get(new HashMap<>());
    }
}

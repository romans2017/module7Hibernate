package ua.goit.module4.queries;

import ua.goit.module4.connectors.dbcontrollers.DbConnector;
import ua.goit.module4.models.DbModel;
import ua.goit.module4.models.DevelopersProjects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DevelopersProjectsQuery extends AbstractQuery{

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
    public List<DevelopersProjects> get(Map<String, Object> simpleFilter) {
        ResultSet resultSet = read(simpleFilter);
        List<DevelopersProjects> list = new ArrayList<>();

        try {
            while (resultSet.next()) {
                DevelopersProjects developersProjects = new DevelopersProjects();
                developersProjects.setId(resultSet.getInt("id"));
                developersProjects.setProject_id(resultSet.getInt("project_id"));
                developersProjects.setDeveloper_id(resultSet.getInt("company_id"));
                list.add(developersProjects);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<DevelopersProjects> getAll() {
        return get(new HashMap<>());
    }

}

package ua.module6.projectsystem.queries;

import ua.module6.projectsystem.connectors.dbcontrollers.DbConnector;
import ua.module6.projectsystem.models.DbModel;
import ua.module6.projectsystem.models.DevelopersProjects;
import ua.module6.projectsystem.models.ModelsList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

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
    public int delete(Integer ...primaryKeys) {
        String requestString = "DELETE FROM " +
                getTableName() +
                " WHERE developer_id=? and project_id=?";
        return dbConnector.executeStatementUpdate(requestString, preparedStatement -> {
            try {
                preparedStatement.setInt(1, primaryKeys[0]);
                preparedStatement.setInt(2, primaryKeys[1]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public int update(DbModel dbModel, Integer... primaryKeys) {
        Set<Map.Entry<String, Integer>> entries = mappingFieldsColumnTypes;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE ")
                .append(getTableName())
                .append(" SET ");

        entries.forEach(entry -> stringBuilder.append(entry.getKey()).append("=?,"));
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        stringBuilder.append(" WHERE developer_id=? and project_id=?");
        String requestString = stringBuilder.toString();

        return dbConnector.executeStatementUpdate(requestString, preparedStatement -> {
            try {
                int parameterIndex = 1;
                for (Map.Entry<String, Integer> entry : entries) {
                    Object value = dbModel.get(entry.getKey());
                    if (value == null) {
                        preparedStatement.setNull(parameterIndex, entry.getValue());
                    } else {
                        preparedStatement.setObject(parameterIndex, value, entry.getValue());
                    }
                    parameterIndex++;
                }
                preparedStatement.setInt(entries.size() + 1, primaryKeys[0]);
                preparedStatement.setInt(entries.size() + 2, primaryKeys[1]);
            } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
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

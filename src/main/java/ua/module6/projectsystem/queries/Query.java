package ua.module6.projectsystem.queries;

import ua.module6.projectsystem.models.DbModel;
import ua.module6.projectsystem.models.ModelsList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public interface Query {

    String getTableName();
    ModelsList normalizeSqlResponse(ResultSet resultSet) throws SQLException;

    int create(DbModel dbModel);
    int update(DbModel dbModel, Integer ...primaryKeys);
    int delete(Integer ...primaryKeys);
    ModelsList get(Map<String, ?> simpleFilter) throws SQLException;

    default ModelsList getAll() throws SQLException {
        return get(new HashMap<>());
    }

    int addToBindingTable(String bindingTableName, Map<String, Integer> mapPrimaryKeys);
    int removeFromBindingTable(String bindingTableName, Map<String, Integer> mapPrimaryKeys);
    ModelsList getFromBindingTable(Query sourceQuery, String bindingTableName, String bindingFieldName, Map<String, Integer> mapPrimaryKeys) throws SQLException;

}

package ua.module7.hibernate.queries;

import ua.module7.hibernate.models.DbModel;
import ua.module7.hibernate.models.ModelsList;

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

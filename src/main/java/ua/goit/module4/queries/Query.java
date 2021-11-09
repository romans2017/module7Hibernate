package ua.goit.module4.queries;

import ua.goit.module4.models.DbModel;
import ua.goit.module4.models.ModelsList;

import java.sql.SQLException;
import java.util.Map;

public interface Query {

    int create(DbModel dbModel);
    int update(DbModel dbModel, Integer primaryKey);
    int delete(Integer primaryKey);
    ModelsList get(Map<String, Object> simpleFilter) throws SQLException;

}

package ua.goit.module4.queries;

import ua.goit.module4.models.DbModel;

import java.sql.ResultSet;
import java.util.Map;

public interface Query {

    int create(DbModel dbModel);
    ResultSet read(Map<String, Object> simpleFilter);
    int update(DbModel dbModel, Integer primaryKey);
    int delete(Integer primaryKey);

}

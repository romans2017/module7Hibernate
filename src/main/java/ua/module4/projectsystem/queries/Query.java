package ua.module4.projectsystem.queries;

import ua.module4.projectsystem.models.DbModel;
import ua.module4.projectsystem.models.ModelsList;

import java.sql.SQLException;
import java.util.Map;

public interface Query {

    int create(DbModel dbModel);
    int update(DbModel dbModel, Integer primaryKey);
    int delete(Integer primaryKey);
    ModelsList get(Map<String, Object> simpleFilter) throws SQLException;

}

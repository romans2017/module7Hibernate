package ua.module6.projectsystem.queries;

import ua.module6.projectsystem.models.DbModel;
import ua.module6.projectsystem.models.ModelsList;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public interface Query {

    int create(DbModel dbModel);
    int update(DbModel dbModel, Integer ...primaryKeys);
    int delete(Integer ...primaryKeys);
    ModelsList get(Map<String, Object> simpleFilter) throws SQLException;

    default ModelsList getAll() throws SQLException {
        return get(new HashMap<>());
    }

}

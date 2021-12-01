package ua.module6.projectsystem.queries;

import ua.module6.projectsystem.connectors.dbcontrollers.DbConnector;
import ua.module6.projectsystem.models.DbModel;
import ua.module6.projectsystem.models.ModelsList;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

abstract class AbstractQuery implements Query {

    protected final DbConnector dbConnector;
    protected ResultSetMetaData columnsTypes;
    protected final Set<Map.Entry<String, Integer>> mappingFieldsColumnTypes;
    protected final Set<Map.Entry<String, Integer>> mappingFieldsColumnTypesForReading;

    abstract protected String getTableName();

    abstract protected Class<? extends DbModel> getTableClass();

    abstract protected ModelsList normalizeSqlResponse(ResultSet resultSet) throws SQLException;

    protected AbstractQuery(DbConnector dbConnector) {
        this.dbConnector = dbConnector;
        try {
            this.columnsTypes = getMeta();
        } catch (SQLException ignored) {
        }
        this.mappingFieldsColumnTypes = mapFieldsToColumnTypes(getTableClass(), false);
        this.mappingFieldsColumnTypesForReading = mapFieldsToColumnTypes(getTableClass(), true);
    }

    private ResultSetMetaData getMeta() throws SQLException {
        return dbConnector
                .executeStatementQuery("SELECT * FROM " + getTableName() + " WHERE 1=0", preparedStatement -> {
                })
                .getMetaData();
    }

    private Set<Map.Entry<String, Integer>> mapFieldsToColumnTypes(Class<? extends DbModel> classModel, boolean isForReading) {
        //get map from class: key is Field.name, value is 12 (type VARCHAR as default)
        Map<String, Integer> mapFields = Arrays.stream(classModel.getDeclaredFields())
                .map(Field::getName)
                .filter(name -> !name.equals("id"))
                .collect(Collectors.toMap(item -> item, item -> -1000));

        //fill map with SQL columns' types
        try {
            for (int i = 1; i <= columnsTypes.getColumnCount(); i++) {
                if (mapFields.containsKey(columnsTypes.getColumnName(i))) {
                    mapFields.put(columnsTypes.getColumnName(i), columnsTypes.getColumnType(i));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (isForReading) {
            //fill type VARCHAR in elements which are not present in SQL table
            return mapFields
                    .entrySet()
                    .stream()
                    .peek(item -> {
                        if (item.getValue() == -1000)
                            item.setValue(Types.VARCHAR);
                    })
                    .collect(Collectors.toSet());
        } else {
            //remove elements which are not present in SQL table
            return mapFields
                    .entrySet()
                    .stream()
                    .filter(item -> item.getValue() != -1000)
                    .collect(Collectors.toSet());
        }
    }

    protected StringBuilder getAdvancedMainRequest() {

        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append("SELECT * FROM ")
                .append(getTableName());

    }

    @Override
    public int create(DbModel dbModel) {

        Set<Map.Entry<String, Integer>> entries = mappingFieldsColumnTypes;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO ")
                .append(getTableName())
                .append("(");

        entries.forEach(entry -> stringBuilder.append(entry.getKey()).append(","));
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());

        stringBuilder.append(") VALUES (");
        entries.forEach(entry -> stringBuilder.append("?,"));
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());

        stringBuilder.append(")");
        String requestString = stringBuilder.toString();
        return dbConnector.executeStatementUpdate(requestString, preparedStatement -> {
            int parameterIndex = 1;
            for (Map.Entry<String, Integer> entry : entries) {
                try {
                    Object value = dbModel.get(entry.getKey());
                    if (value == null) {
                        preparedStatement.setNull(parameterIndex, entry.getValue());
                    } else {
                        preparedStatement.setObject(parameterIndex, value, entry.getValue());
                    }
                } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                parameterIndex++;
            }
        });
    }

    @Override
    public int update(DbModel dbModel, Integer primaryKey) {

        Set<Map.Entry<String, Integer>> entries = mappingFieldsColumnTypes;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE ")
                .append(getTableName())
                .append(" SET ");

        entries.forEach(entry -> stringBuilder.append(entry.getKey()).append("=?,"));
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        stringBuilder.append(" WHERE id=?");
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
                preparedStatement.setInt(entries.size() + 1, primaryKey);
            } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public int delete(Integer primaryKey) {

        String requestString = "DELETE FROM " +
                getTableName() +
                " WHERE id=?";
        return dbConnector.executeStatementUpdate(requestString, preparedStatement -> {
            try {
                preparedStatement.setInt(1, primaryKey);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private ResultSet readDb(StringBuilder mainRequest, Map<String, Object> simpleFilter) {
        Set<Map.Entry<String, Integer>> entries = mappingFieldsColumnTypesForReading;

        StringBuilder whereStringBuilder = new StringBuilder();
        Set<Map.Entry<String, Integer>> filteredEntries = entries.stream()
                .filter(item -> simpleFilter.containsKey(item.getKey()))
                .peek(item -> whereStringBuilder.append(item.getKey()).append("=").append("?").append(" and "))
                .collect(Collectors.toSet());
        if (simpleFilter.get("id") != null) {
            whereStringBuilder
                    .append(getTableName())
                    .append(".id=?");
            filteredEntries.add(Map.entry("id", Types.INTEGER));
        }
        if (whereStringBuilder.length() > 0) {
            String whereString = whereStringBuilder.toString();
            if (whereString.endsWith("and ")) {
                whereString += "1=1";
            }
            mainRequest.append(" WHERE ").append(whereString);
        }

        String requestString = mainRequest.toString();

        return dbConnector.executeStatementQuery(requestString, preparedStatement -> {
            try {
                int parameterIndex = 1;
                for (Map.Entry<String, Integer> entry : filteredEntries) {
                    Object valueFilter = simpleFilter.get(entry.getKey());
                    if (valueFilter == null) {
                        preparedStatement.setNull(parameterIndex, entry.getValue());
                    } else {
                        preparedStatement.setObject(parameterIndex, valueFilter, entry.getValue());
                    }
                    parameterIndex++;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public ModelsList get(Map<String, Object> simpleFilter) throws SQLException {
        return normalizeSqlResponse(readDb(getAdvancedMainRequest(), simpleFilter));
    }

}

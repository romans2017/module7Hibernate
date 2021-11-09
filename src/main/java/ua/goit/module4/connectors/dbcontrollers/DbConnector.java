package ua.goit.module4.connectors.dbcontrollers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.function.Consumer;

public interface DbConnector {

    boolean executeStatement(String sql, Consumer<PreparedStatement> params);
    ResultSet executeStatementQuery(String sql, Consumer<PreparedStatement> params);
    int executeStatementUpdate(String sql, Consumer<PreparedStatement> params);

}

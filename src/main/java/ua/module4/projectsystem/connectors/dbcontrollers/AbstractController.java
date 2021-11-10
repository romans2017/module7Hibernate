package ua.module4.projectsystem.connectors.dbcontrollers;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;

abstract class AbstractController implements DbConnector {

    protected DataSource connectionDataSource;

    @Override
    public boolean executeStatement(String sql, Consumer<PreparedStatement> params) {

        boolean result = false;
        try (Connection connection = connectionDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            params.accept(preparedStatement);
            result = preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ResultSet executeStatementQuery(String sql, Consumer<PreparedStatement> params) {

        ResultSet result = null;
        try (Connection connection = connectionDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            params.accept(preparedStatement);
            result = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int executeStatementUpdate(String sql, Consumer<PreparedStatement> params) {

        int result = -1;
        try (Connection connection = connectionDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            params.accept(preparedStatement);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}

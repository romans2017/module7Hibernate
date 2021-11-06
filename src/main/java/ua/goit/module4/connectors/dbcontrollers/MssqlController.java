package ua.goit.module4.connectors.dbcontrollers;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.flywaydb.core.Flyway;

import java.util.Properties;

public class MssqlController extends AbstractController {

    public MssqlController(Properties properties) {

        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setServerName(properties.getProperty("db.host"));
        dataSource.setPortNumber(Integer.parseInt(properties.getProperty("db.port")));
        dataSource.setDatabaseName(properties.getProperty("db.databaseName"));
        dataSource.setUser(properties.getProperty("db.username"));
        dataSource.setPassword(properties.getProperty("db.password"));

        connectionDataSource = dataSource;

        Flyway flyway = Flyway.configure()
                .locations("db/migration/mssql")
                .baselineOnMigrate(true)
                .dataSource(dataSource)
                .load();
        flyway.migrate();
    }

}

package ua.module6.projectsystem.connectors.dbcontrollers;

import org.flywaydb.core.Flyway;
import org.postgresql.ds.PGSimpleDataSource;

import java.util.Properties;

public class PostgresController extends AbstractController {

    public PostgresController(Properties properties) {

        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerNames(new String[]{properties.getProperty("db.host")});
        dataSource.setPortNumbers(new int[]{Integer.parseInt(properties.getProperty("db.port"))});
        dataSource.setDatabaseName(properties.getProperty("db.databaseName"));
        dataSource.setUser(properties.getProperty("db.username"));
        dataSource.setPassword(properties.getProperty("db.password"));

        connectionDataSource = dataSource;

        Flyway flyway = Flyway.configure()
                .locations("db/migration/postgres")
                .baselineOnMigrate(true)
                .dataSource(dataSource)
                .load();
        flyway.migrate();
    }

}

package ua.goit.module4.connectors;

import ua.goit.module4.connectors.dbcontrollers.DbConnector;
import ua.goit.module4.connectors.dbcontrollers.MssqlController;
import ua.goit.module4.connectors.dbcontrollers.PostgresController;

import java.util.Objects;
import java.util.Properties;

public class ConnectorManager {

    public static DbConnector connectTo(DbType dbType) {
        Properties properties = new Properties();
        if (dbType == DbType.POSTGRES) {
            properties = PropertiesService.readProperties("src/main/resources/postgres.properties");
        } else if (dbType == DbType.MSSQL) {
            properties = PropertiesService.readProperties("src/main/resources/mssql.properties");
        }
        if (!PropertiesService.isValidDbProperties(properties)) {
            return null;
        }
        if (Objects.equals(properties.getProperty("db.type"), "postgres")) {
            return new PostgresController(properties);
        } else if (Objects.equals(properties.getProperty("db.type"), "postgres")) {
            return new MssqlController(properties);
        } else {
            return null;
        }
    }

}

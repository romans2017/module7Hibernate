package ua.module7.hibernate.connectors;

import ua.module7.hibernate.connectors.dbcontrollers.DbConnector;
import ua.module7.hibernate.connectors.dbcontrollers.PostgresController;
import ua.module7.hibernate.services.PropertiesService;

import java.util.Objects;
import java.util.Properties;

public class ConnectorManager {

    public static DbConnector connectTo(DbType dbType) {
        Properties properties = new Properties();
        if (dbType == DbType.POSTGRES) {
            properties = PropertiesService.readProperties("postgres.properties");
        }
        if (!PropertiesService.isValidDbProperties(properties)) {
            return null;
        }
        if (Objects.equals(properties.getProperty("db.type"), "postgres")) {
            return new PostgresController(properties);
        } else {
            return null;
        }
    }

}

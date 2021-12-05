package ua.module7.hibernate.services;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class PropertiesService {
    public static Properties readProperties(String fileProperties) {
        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileProperties));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static boolean isValidDbProperties(Properties properties) {
        Set<String> setProperties = properties.stringPropertyNames();
        return setProperties.contains("db.type")
                && setProperties.contains("db.host")
                && setProperties.contains("db.port")
                && setProperties.contains("db.databaseName")
                && setProperties.contains("db.username")
                && setProperties.contains("db.password");
    }
}

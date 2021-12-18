package ua.module7.hibernate.connectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnitUtil;

public class PersistanceConnecter {
    private static final EntityManagerFactory entityManagerFactory;
    static {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("module7Hibernate");
        } catch (Throwable t) {
            t.printStackTrace();
            throw t;
        }
    }

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public static PersistenceUnitUtil getUtil() {
        return entityManagerFactory.getPersistenceUnitUtil();
    }
}

package ua.module7.hibernate.dao;

import ua.module7.hibernate.connectors.PersistanceConnecter;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

abstract public class AbstractDao<T> implements Dao<T> {

    protected final EntityManager entityManager = PersistanceConnecter.getEntityManager();
    private final Class<T> entityClass;

    @SuppressWarnings("unchecked")
    protected AbstractDao() {
        Type[] params = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
        entityClass = (Class<T>) params[0];
    }

    @Override
    public void create(T entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.persist(entity);
        } catch (Throwable e) {
            transaction.rollback();
            e.printStackTrace();
        }
        if (transaction.isActive()) {
            transaction.commit();
        }
    }

    @Override
    public T read(Integer primaryKey) {
        EntityGraph<?> graph = entityManager.getEntityGraph(entityClass.getSimpleName() + "Graph");
        return entityManager.find(entityClass, primaryKey, Map.of("javax.persistence.loadgraph", graph));
    }

    @Override
    public <K> List<K> readAll(Class<K> toClass) {
        return entityManager
                .createQuery("FROM " + entityClass.getSimpleName() + " ORDER BY id", toClass)
                .getResultList();
    }

    @Override
    public T update(T entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        T mergedEntity = null;
        transaction.begin();
        try {
            mergedEntity = entityManager.merge(entity);
        } catch (Throwable e) {
            transaction.rollback();
            e.printStackTrace();
        }
        if (transaction.isActive()) {
            transaction.commit();
        }
        return mergedEntity;
    }

    @Override
    abstract public void delete(T entity);
}

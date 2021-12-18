package ua.module7.hibernate.dao;

import java.util.List;

public interface Dao<T> {
    void create(T entity);
    T read(Integer primaryKey);
    <K> List<K> readAll(Class<K> toClass);
    T update(T entity);
    void delete(T entity);
}

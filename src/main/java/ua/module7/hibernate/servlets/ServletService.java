package ua.module7.hibernate.servlets;

import ua.module7.hibernate.dao.Dao;
import ua.module7.hibernate.pojo.Pojo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ServletService {

    public static <E extends Pojo> E getDbModel(Integer id, Dao<E> serviceDao, Class<E> classModel) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        E entity = serviceDao.read(id);
        if (entity != null) {
            return entity;
        } else {
            Constructor<E> constructor = classModel.getConstructor();
            return constructor.newInstance().initEmpty();
        }
    }

    public static <E extends Pojo, D> List<D> getAllModels(Dao<E> serviceDao, Class<D> modelClass) {
        return serviceDao.readAll(modelClass);
    }
}

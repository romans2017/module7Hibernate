package ua.module7.hibernate.models;

public interface DbModel {

    Object get(String nameField) throws NoSuchFieldException, IllegalAccessException;

}

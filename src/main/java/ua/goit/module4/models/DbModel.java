package ua.goit.module4.models;

public interface DbModel {

    Object get(String nameField) throws NoSuchFieldException, IllegalAccessException;

}

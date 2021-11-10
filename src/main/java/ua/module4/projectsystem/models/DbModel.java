package ua.module4.projectsystem.models;

public interface DbModel {

    Object get(String nameField) throws NoSuchFieldException, IllegalAccessException;

}

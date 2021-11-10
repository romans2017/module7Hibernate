package ua.module4.projectsystem.models;

import java.lang.reflect.Field;

abstract class AbstractModel implements DbModel {

    public Object get(String nameField) throws NoSuchFieldException, IllegalAccessException {
        Field field = this.getClass().getDeclaredField(nameField);
        field.setAccessible(true);
        return field.get(this);
    }

}

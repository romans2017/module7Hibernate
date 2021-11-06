package ua.goit.module4.models;

import java.lang.reflect.Field;

abstract public class AbstractModel implements DbModel {

    public Object get(String nameField) throws NoSuchFieldException, IllegalAccessException {
        Field field = this.getClass().getDeclaredField(nameField);
        field.setAccessible(true);
        return field.get(this);
    }

}

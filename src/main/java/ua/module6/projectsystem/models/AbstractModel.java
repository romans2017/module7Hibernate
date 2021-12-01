package ua.module6.projectsystem.models;

import java.lang.reflect.Field;
import java.util.Optional;

abstract class AbstractModel implements DbModel {

    public Object get(String nameField) throws NoSuchFieldException, IllegalAccessException {
        Field field = this.getClass().getDeclaredField(nameField);
        field.setAccessible(true);
        return Optional.ofNullable(field.get(this)).orElse(new Object());
    }

}

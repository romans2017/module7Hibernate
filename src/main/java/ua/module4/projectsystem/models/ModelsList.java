package ua.module4.projectsystem.models;

import java.util.ArrayList;

public class ModelsList extends ArrayList<DbModel> {
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (DbModel item: this) {
            stringBuilder.append(item.toString()).append("\n");
        }
        return stringBuilder.toString().trim();
    }
}

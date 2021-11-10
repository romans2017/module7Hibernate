package ua.module4.projectsystem.console.menus;

import ua.module4.projectsystem.console.InputConsole;
import ua.module4.projectsystem.console.MappingClasses;
import ua.module4.projectsystem.console.MenuModel;
import ua.module4.projectsystem.models.DbModel;
import ua.module4.projectsystem.models.ModelsList;
import ua.module4.projectsystem.queries.Query;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Consumer;

abstract class AbstractMenu implements Menu {
    protected MenuModel menuModel;
    protected Query query;
    protected Menu previousMenu;

    private String additionalParameter;
    private String values;
    private Class<? extends DbModel> classDbModel;
    private MappingClasses enumItem;

    @Override
    public MenuModel getMenuModel() {
        return menuModel;
    }

    protected Consumer<String> createLambda() {

        return param -> {

            init(param);
            Map<String, Object> filter = addParameter(false);

            String[] params = values.split("--", -1);

            if (params.length - 1 < enumItem.getNumberOfMainFields()) {
                formatError("Incorrect input format!!!");
                return;
            }
            Constructor<? extends DbModel> constructor;
            try {
                constructor = classDbModel.getConstructor(String[].class);
                DbModel dbModel = constructor.newInstance((Object) params);
                if (query.create(dbModel) == 0) throw new SQLException();
                System.out.println(query.get(filter));
            } catch (ArrayIndexOutOfBoundsException | SQLException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

            this.showMenu();
        };
    }

    protected Consumer<String> updateLambda() {
        return param -> {

            init(param);

            String[] params = values.split("--", -1);

            if (params.length - 1 < enumItem.getNumberOfMainFields() + 1) {
                formatError("Incorrect input format!!!");
                return;
            }

            ModelsList res = new ModelsList();
            try {
                res = query.get(Map.of("id", Integer.parseInt(params[1].trim())));
            } catch (NumberFormatException e) {
                formatError("Incorrect input format!!!");
                return;
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (res.size() == 0) {
                formatError("Element with id = " + params[1].trim() + " does not exist");
                return;
            }
            Constructor<? extends DbModel> constructor;
            try {
                constructor = classDbModel.getConstructor(res.get(0).getClass(), String[].class);
                DbModel dbModel = constructor.newInstance(res.get(0), params);
                if (query.update(dbModel, Integer.parseInt(params[1].trim())) == 0) throw new SQLException();
                System.out.println(query.get(new HashMap<>()));
            } catch (ArrayIndexOutOfBoundsException | SQLException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            this.showMenu();
        };
    }

    protected Consumer<String> deleteLambda() {
        return param -> {

            init(param);
            String[] params = values.split("--", -1);

            if (params.length < 2) {
                formatError("Incorrect input format!!!");
                return;
            }

            Map<String, Object> filter = addParameter(false);

            ModelsList res = new ModelsList();
            try {
                res = query.get(Map.of("id", Integer.parseInt(params[1].trim())));
            } catch (NumberFormatException e) {
                formatError("Incorrect input format!!!");
                return;
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (res.size() == 0) {
                formatError("Element with id = " + params[1] + " does not exist");
                return;
            }

            try {
                if (query.delete(Integer.parseInt(params[1].trim())) == 0) throw new SQLException();
                System.out.println(query.get(filter));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            this.showMenu();
        };
    }

    protected Consumer<String> readLambda() {
        return param -> {

            init(param);
            addParameter(true);

            String[] params = values.split("--", -1);

            Map<String, Object> filter = new HashMap<>();
            try {
                for (int i = 1; i < params.length; i++) {
                    String[] condition = params[i].split("=", -1);
                    String left = condition[0].trim().toLowerCase(Locale.ROOT);
                    String right = condition[1].trim();
                    Field field;
                    try {
                        field = classDbModel.getDeclaredField(left);
                    } catch (NoSuchFieldException e) {
                        continue;
                    }
                    if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
                        filter.put(left, Integer.parseInt(right));
                    } else {
                        filter.put(left, right);
                    }
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                formatError(e.getMessage());
                return;
            }

            try {
                System.out.println(query.get(filter));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            this.showMenu();
        };
    }

    protected void setAdditionalParameter(String additionalParameter) {
        this.additionalParameter = additionalParameter;
    }

    protected Menu getPreviousMenu() {
        return previousMenu;
    }

    protected void setPreviousMenu(Menu previousMenu) {
        this.previousMenu = previousMenu;
    }

    protected Consumer<String> openDevSkillsMenu() {
        return param -> {
            String[] params = param.split("--", -1);
            if (params.length < 2) {
                return;
            }
            DevelopersSkillsMenu menu = DevelopersSkillsMenu.getInstance();
            menu.setAdditionalParameter(params[1].trim());
            InputConsole.getInstance().setCurrentMenu(menu);
            menu.setPreviousMenu(this);
            menu.showMenu();
        };
    }

    protected Consumer<String> openDevProjectsMenu() {
        return param -> {
            String[] params = param.split("--", -1);
            if (params.length < 2) {
                return;
            }
            DevelopersProjectsMenu menu = DevelopersProjectsMenu.getInstance();
            menu.setAdditionalParameter(params[1].trim());
            InputConsole.getInstance().setCurrentMenu(menu);
            menu.setPreviousMenu(this);
            menu.showMenu();
        };
    }

    private void formatError(String Error) {
        System.out.println(Error);
        this.showMenu();
    }

    private void init(String param) {
        enumItem = MappingClasses.getEnumByMenu(this.getClass());
        if (enumItem == null) {
            throw new NullPointerException();
        }
        classDbModel = enumItem.getClassDbModel();

        param = param.trim();
        values = "";
        if (param.contains(" ")) {
            values = param.substring(param.indexOf(" ") + 1).trim();
        }
    }

    private Map<String, Object> addParameter(boolean isRead) {

        Map<String, Object> filter = new HashMap<>();
        if (additionalParameter != null) {
            if (this.getClass().equals(DevelopersSkillsMenu.class)) {
                if (previousMenu.getClass().equals(DeveloperMenu.class)) {
                    if (isRead) {
                        values += "--developer_id=" + additionalParameter;
                    } else {
                        values = "--" + additionalParameter + values;
                    }
                    filter.put("developer_id", Integer.parseInt(additionalParameter));
                } else {
                    if (isRead) {
                        values += "--skill_id=" + additionalParameter;
                    } else {
                        values = values + "--" + additionalParameter;
                    }
                    filter.put("skill_id", Integer.parseInt(additionalParameter));
                }
            } else if (this.getClass().equals(DevelopersProjectsMenu.class)) {
                if (previousMenu.getClass().equals(DeveloperMenu.class)) {
                    if (isRead) {
                        values += "--developer_id=" + additionalParameter;
                    } else {
                        values = "--" + additionalParameter + values;
                    }
                    filter.put("developer_id", Integer.parseInt(additionalParameter));
                } else {
                    if (isRead) {
                        values += "--project_id=" + additionalParameter;
                    } else {
                        values = values + "--" + additionalParameter;
                    }
                    filter.put("project_id", Integer.parseInt(additionalParameter));
                }
            }
        }
        return filter;
    }

}

package ua.goit.module4.console.menus;

import ua.goit.module4.console.InputConsole;
import ua.goit.module4.console.MappingClasses;
import ua.goit.module4.models.DbModel;
import ua.goit.module4.models.ModelsList;
import ua.goit.module4.queries.Query;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Consumer;

abstract class AbstractMenu implements Menu {
    protected MenuModel menuModel;
    protected Query query;
    protected String addParam;
    protected Menu previousMenu;

    @Override
    public MenuModel getMenuModel() {
        return menuModel;
    }

    protected Consumer<String> createLambda() {
        return param -> {
            MappingClasses enumItem = MappingClasses.getEnumByMenu(this.getClass());
            if (enumItem == null) {
                throw new NullPointerException();
            }
            Class<? extends DbModel> classDbModel = enumItem.getClassDbModel();

            param = param.trim();
            String values = "";
            if (param.contains(" ")) {
                values = param.substring(param.indexOf(" ") + 1).trim();
            }

            Map<String, Object> filter = new HashMap<>();
            if (addParam != null) {
                if (this.getClass().equals(DevelopersSkillsMenu.class)) {
                    if (previousMenu.getClass().equals(DeveloperMenu.class)) {
                        values = "--" + addParam + values;
                        filter.put("developer_id", Integer.parseInt(addParam));
                    } else {
                        values = values + "--" + addParam;
                        filter.put("skill_id", Integer.parseInt(addParam));
                    }
                } else if (this.getClass().equals(DevelopersProjectsMenu.class)) {
                    if (previousMenu.getClass().equals(DeveloperMenu.class)) {
                        values = "--" + addParam + values;
                        filter.put("developer_id", Integer.parseInt(addParam));
                    } else {
                        values = values + "--" + addParam;
                        filter.put("project_id", Integer.parseInt(addParam));
                    }
                }
            }

            String[] params = values.split("--", -1);
            List<String> paramList = Arrays.stream(params).toList();
            params = paramList.toArray(new String[0]);

            if (params.length < enumItem.getNumberOfMainFields()) {
                System.out.println("Incorrect input format!!!");
                this.showMenu();
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
            MappingClasses enumItem = MappingClasses.getEnumByMenu(this.getClass());
            if (enumItem == null) {
                throw new NullPointerException();
            }
            Class<? extends DbModel> classDbModel = enumItem.getClassDbModel();
            param = param.trim();
            String values = "";
            if (param.contains(" ")) {
                values = param.substring(param.indexOf(" ") + 1).trim();
            }
            String[] params = values.split("--", -1);

            if (params.length - 1 < enumItem.getNumberOfMainFields() + 1) {
                System.out.println("Incorrect input format!!!");
                this.showMenu();
                return;
            }

            ModelsList res = new ModelsList();
            try {
                res = query.get(Map.of("id", Integer.parseInt(params[1].trim())));
            } catch (NumberFormatException | SQLException e) {
                e.printStackTrace();
            }

            if (res.size() == 0) {
                System.out.println("Element with id = " + params[1].trim() + " does not exist");
                this.showMenu();
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
            param = param.trim();
            String values = "";
            if (param.contains(" ")) {
                values = param.substring(param.indexOf(" ") + 1).trim();
            }
            String[] params = values.split("--", -1);

            if (params.length < 2) {
                System.out.println("Incorrect input format!!!");
                this.showMenu();
                return;
            }

            Map<String, Object> filter = new HashMap<>();
            if (addParam != null) {
                if (this.getClass().equals(DevelopersSkillsMenu.class)) {
                    if (previousMenu.getClass().equals(DeveloperMenu.class)) {
                        filter.put("developer_id", Integer.parseInt(addParam));
                    } else {
                        filter.put("skill_id", Integer.parseInt(addParam));
                    }
                } else if (this.getClass().equals(DevelopersProjectsMenu.class)) {
                    if (previousMenu.getClass().equals(DeveloperMenu.class)) {
                        filter.put("developer_id", Integer.parseInt(addParam));
                    } else {
                        filter.put("project_id", Integer.parseInt(addParam));
                    }
                }
            }

            ModelsList res = new ModelsList();
            try {
                res = query.get(Map.of("id", Integer.parseInt(params[1].trim())));
            } catch (NumberFormatException | SQLException e) {
                e.printStackTrace();
            }

            if (res.size() == 0) {
                System.out.println("Element with id = " + params[1] + " does not exist");
                this.showMenu();
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
            MappingClasses enumItem = MappingClasses.getEnumByMenu(this.getClass());
            if (enumItem == null) {
                throw new NullPointerException();
            }
            Class<? extends DbModel> classDbModel = enumItem.getClassDbModel();
            param = param.trim();
            String values = "";
            if (param.contains(" ")) {
                values = param.substring(param.indexOf(" ") + 1).trim();
            }
            if (addParam != null) {
                if (this.getClass().equals(DevelopersSkillsMenu.class)) {
                    if (previousMenu.getClass().equals(DeveloperMenu.class)) {
                        values += "--developer_id=" + addParam;
                    } else {
                        values += "--skill_id=" + addParam;
                    }
                } else if (this.getClass().equals(DevelopersProjectsMenu.class)) {
                    if (previousMenu.getClass().equals(DeveloperMenu.class)) {
                        values += "--developer_id=" + addParam;
                    } else {
                        values += "--project_id=" + addParam;
                    }
                }
            }

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
                e.printStackTrace();
                this.showMenu();
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

    protected void setAddParam(String addParam) {
        this.addParam = addParam;
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
            if (params.length < 1) {
                return;
            }
            DevelopersSkillsMenu menu = DevelopersSkillsMenu.getInstance(); //developer_id
            menu.setAddParam(params[1].trim());
            InputConsole.getInstance().setCurrentMenu(menu);
            menu.setPreviousMenu(this);
            menu.showMenu();
        };
    }

    protected Consumer<String> openDevProjectsMenu() {
        return param -> {
            String[] params = param.split("--", -1);
            if (params.length < 1) {
                return;
            }
            DevelopersProjectsMenu menu = DevelopersProjectsMenu.getInstance(); //developer_id
            menu.setAddParam(params[1].trim());
            InputConsole.getInstance().setCurrentMenu(menu);
            menu.setPreviousMenu(this);
            menu.showMenu();
        };
    }
}

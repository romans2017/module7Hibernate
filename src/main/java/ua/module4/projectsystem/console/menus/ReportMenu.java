package ua.module4.projectsystem.console.menus;

import ua.module4.projectsystem.console.InputConsole;
import ua.module4.projectsystem.console.MenuModel;
import ua.module4.projectsystem.models.*;
import ua.module4.projectsystem.queries.*;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;

public class ReportMenu extends AbstractMenu {
    private static ReportMenu instance;

    public static ReportMenu getInstance() {
        if (instance == null) {
            instance = new ReportMenu();
        }
        return instance;
    }

    protected Consumer<String> readLambda(Class<? extends DbModel> classDbModel, Query reportQuery) {
        return param -> {

            String values;
            param = param.trim();
            values = "";
            if (param.contains(" ")) {
                values = param.substring(param.indexOf(" ") + 1).trim();
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
                System.out.println(reportQuery.get(filter));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            this.showMenu();
        };
    }

    private ReportMenu() {
        menuModel = new MenuModel();
        menuModel.put("salaries", readLambda(ReportDevelopersSalary.class,
                ReportDevelopersSalaryQuery.getInstance(InputConsole.getInstance().getDbConnector())));
        menuModel.put("developers", readLambda(ReportDevelopersProjects.class,
                ReportDevelopersProjectsQuery.getInstance(InputConsole.getInstance().getDbConnector())));
        menuModel.put("java", readLambda(ReportDevelopers.class,
                ReportDevelopersJavaQuery.getInstance(InputConsole.getInstance().getDbConnector())));
        menuModel.put("middle", readLambda(ReportDevelopers.class,
                ReportDevelopersMiddleQuery.getInstance(InputConsole.getInstance().getDbConnector())));
        menuModel.put("projects", readLambda(ReportProjects.class,
                ReportProjectsQuery.getInstance(InputConsole.getInstance().getDbConnector())));
        menuModel.put("up", param -> {
            Menu menu = getPreviousMenu();
            InputConsole.getInstance().setCurrentMenu(menu);
            menu.showMenu();
        });
        menuModel.put("exit", params -> System.exit(0));
    }

    @Override
    public void showMenu() {
        String textMenu = """
                
                ******** REPORTS MENU ********
                Salaries by project (command: salaries --project_id=value)
                Developers by project (command: developers --project_id=value)
                Java developers (command: java)
                Middle developers (command: middle)
                Projects (command: projects)
                UP...
                EXIT...
                ******** ********
                """;
        System.out.println(textMenu);
    }
}

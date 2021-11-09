package ua.goit.module4.console.menus;

import ua.goit.module4.console.InputConsole;
import ua.goit.module4.queries.DevelopersProjectsQuery;

public class DevelopersProjectsMenu extends AbstractMenu{

    private static DevelopersProjectsMenu instance;

    public static DevelopersProjectsMenu getInstance() {
        if (instance == null) {
            instance = new DevelopersProjectsMenu();
        }
        return instance;
    }

    private DevelopersProjectsMenu() {

        query = DevelopersProjectsQuery.getInstance(InputConsole.getInstance().getDbConnector());

        menuModel = new MenuModel();
        menuModel.put("add", createLambda());
        menuModel.put("delete", deleteLambda());
        menuModel.put("read", readLambda());
        menuModel.put("up", params -> {
            Menu menu = getPreviousMenu();
            InputConsole.getInstance().setCurrentMenu(menu);
            menu.showMenu();
        });
        menuModel.put("exit", params -> System.exit(0));
    }

    @Override
    public void showMenu() {
        String textMenu;
        if (previousMenu.getClass().equals(DeveloperMenu.class)) {
            textMenu = """
                                    
                    ******** Developer's projects MENU ********
                    add (command: add --project_id)
                    read (command: read --id=value(optional) --developer_id=value(optional) --project_id=value(optional))
                    delete (command: delete --id)
                    UP...
                    EXIT...
                    ******** ********
                    """;
        } else {
            textMenu = """
                                    
                    ******** Developer's projects MENU ********
                    add (command: add --developer_id)
                    read (command: read --id=value(optional) --developer_id=value(optional) --skill_id=value(optional))
                    delete (command: delete --id)
                    UP...
                    EXIT...
                    ******** ********
                    """;

        }
        System.out.println(textMenu);
    }
}

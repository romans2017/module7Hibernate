package ua.module6.projectsystem.console.menus;

import ua.module6.projectsystem.console.InputConsole;
import ua.module6.projectsystem.console.MenuModel;
import ua.module6.projectsystem.queries.DevelopersSkillsQuery;

public class DevelopersSkillsMenu extends AbstractMenu{

    private static DevelopersSkillsMenu instance;

    public static DevelopersSkillsMenu getInstance() {
        if (instance == null) {
            instance = new DevelopersSkillsMenu();
        }
        return instance;
    }

    private DevelopersSkillsMenu() {

        query = DevelopersSkillsQuery.getInstance(InputConsole.getInstance().getDbConnector());

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
                                    
                    ******** Developer's skills MENU ********
                    add (command: add --skill_id)
                    read (command: read --id=value(optional) --developer_id=value(optional) --skill_id=value(optional))
                    delete (command: delete --id)
                    UP... (command 'up')
                    EXIT...
                    ******** ********
                    """;
        } else {
            textMenu = """
                                    
                    ******** Developer's skills MENU ********
                    add (command: add --developer_id)
                    read (command: read --id=value(optional) --developer_id=value(optional) --skill_id=value(optional))
                    delete (command: delete --id)
                    UP... (command 'up')
                    EXIT... (command 'exit')
                    ******** ********
                    """;

        }
        System.out.println(textMenu);
    }
}

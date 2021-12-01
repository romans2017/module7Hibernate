package ua.module6.projectsystem.console.menus;

import ua.module6.projectsystem.console.InputConsole;
import ua.module6.projectsystem.console.MenuModel;
import ua.module6.projectsystem.queries.SkillQuery;

public class SkillMenu extends AbstractMenu{

    private static SkillMenu instance;

    public static SkillMenu getInstance() {
        if (instance == null) {
            instance = new SkillMenu();
        }
        return instance;
    }

    private SkillMenu() {
        query = SkillQuery.getInstance(InputConsole.getInstance().getDbConnector());

        menuModel = new MenuModel();
        menuModel.put("create", createLambda());
        menuModel.put("update", updateLambda());
        menuModel.put("delete", deleteLambda());
        menuModel.put("read", readLambda());
        menuModel.put("developers", openDevSkillsMenu());
        menuModel.put("up", param -> {
            Menu menu = getPreviousMenu();
            InputConsole.getInstance().setCurrentMenu(menu);
            menu.showMenu();
        });
        menuModel.put("exit", param -> System.exit(0));
    }

    @Override
    public void showMenu() {
        String textMenu = """
                
                ******** SKILL MENU ********
                create (command: create --language --level)
                update (command: update --id --(language or empty) --(level or empty))
                read (command: read --id=value(optional) --language=value(optional) --level=value(optional))
                delete (command: delete --id)
                Developers by-> (command: developers --id)
                UP... (command 'up')
                EXIT... (command 'exit')
                ******** ********
                """;
        System.out.println(textMenu);
    }
}

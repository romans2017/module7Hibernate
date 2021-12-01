package ua.module6.projectsystem.console.menus;

import ua.module6.projectsystem.console.InputConsole;
import ua.module6.projectsystem.console.MenuModel;
import ua.module6.projectsystem.queries.DeveloperQuery;

public class DeveloperMenu extends AbstractMenu {
    private static DeveloperMenu instance;

    public static DeveloperMenu getInstance() {
        if (instance == null) {
            instance = new DeveloperMenu();
        }
        return instance;
    }

    private DeveloperMenu() {
        query = DeveloperQuery.getInstance(InputConsole.getInstance().getDbConnector());

        menuModel = new MenuModel();
        menuModel.put("create", createLambda());
        menuModel.put("update", updateLambda());
        menuModel.put("delete", deleteLambda());
        menuModel.put("read", readLambda());
        menuModel.put("skills", openDevSkillsMenu());
        menuModel.put("projects", openDevProjectsMenu());
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
                                
                ******** DEVELOPER MENU ********
                create (command: create --name --age --company_id --salary)
                update (command: update --id --(name or empty) --(age or empty) --(company_id or empty) --(salary or empty))
                read (command: read --id=value(optional) --name=value(optional) --age=value(optional) --company_id=value(optional) --salary=value(optional))
                delete (command: delete --id)
                Skills by-> (command: skills --id)
                Projects by-> (command: projects --id)
                UP... (command 'up') 
                EXIT... (command 'exit')
                ******** ********
                """;
        System.out.println(textMenu);
    }
}

package ua.module6.projectsystem.console.menus;

import ua.module6.projectsystem.console.InputConsole;
import ua.module6.projectsystem.console.MenuModel;
import ua.module6.projectsystem.queries.ProjectQuery;

public class ProjectMenu extends AbstractMenu{
    private static ProjectMenu instance;

    public static ProjectMenu getInstance() {
        if (instance == null) {
            instance = new ProjectMenu();
        }
        return instance;
    }

    private ProjectMenu() {
        query = ProjectQuery.getInstance(InputConsole.getInstance().getDbConnector());

        menuModel = new MenuModel();
        menuModel.put("create", createLambda());
        menuModel.put("update", updateLambda());
        menuModel.put("delete", deleteLambda());
        menuModel.put("read", readLambda());
        menuModel.put("developers", openDevProjectsMenu());
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
                
                ******** PROJECT MENU ********
                create (command: create --name --description --cost --creation_date(YYYY-MM-DD) --company_id --customer_id)
                update (command: update --id --(name or empty) --(description or empty) --(cost or empty) --(creation_date or empty) --(company_id or empty) --(customer_id or empty))
                read (command: read --id=value(optional) --name=value(optional) --description=value(optional) --cost=value(optional) --creation_date=value(optional) --company_id=value(optional) --customer_id=value(optional))
                delete (command: delete --id)
                Developers by-> (command: developers --id)
                UP... (command 'up')
                EXIT... (command 'exit')
                ******** ********
                """;
        System.out.println(textMenu);
    }
}

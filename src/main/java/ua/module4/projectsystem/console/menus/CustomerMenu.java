package ua.module4.projectsystem.console.menus;

import ua.module4.projectsystem.console.InputConsole;
import ua.module4.projectsystem.console.MenuModel;
import ua.module4.projectsystem.queries.CustomerQuery;

public class CustomerMenu extends AbstractMenu {

    private static CustomerMenu instance;

    public static CustomerMenu getInstance() {
        if (instance == null) {
            instance = new CustomerMenu();
        }
        return instance;
    }

    private CustomerMenu() {
        query = CustomerQuery.getInstance(InputConsole.getInstance().getDbConnector());

        menuModel = new MenuModel();
        menuModel.put("create", createLambda());
        menuModel.put("update", updateLambda());
        menuModel.put("delete", deleteLambda());
        menuModel.put("read", readLambda());
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
                
                ******** CUSTOMER MENU ********
                create (command: create --name --country)
                update (command: update --id --(name or empty) --(country or empty))
                read (command: read --id=value(optional) --name=value(optional) --country=value(optional))
                delete (command: delete --id)
                UP...
                EXIT...
                ******** ********
                """;
        System.out.println(textMenu);
    }

}

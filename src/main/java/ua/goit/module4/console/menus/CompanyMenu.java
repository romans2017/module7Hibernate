package ua.goit.module4.console.menus;

import ua.goit.module4.console.InputConsole;
import ua.goit.module4.queries.CompanyQuery;

public class CompanyMenu extends AbstractMenu {

    private static CompanyMenu instance;

    public static CompanyMenu getInstance() {
        if (instance == null) {
            instance = new CompanyMenu();
        }
        return instance;
    }

    private CompanyMenu() {
        query = CompanyQuery.getInstance(InputConsole.getInstance().getDbConnector());

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
                
                ******** COMPANY MENU ********
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

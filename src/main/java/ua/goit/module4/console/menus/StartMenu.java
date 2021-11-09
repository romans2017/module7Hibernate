package ua.goit.module4.console.menus;

import ua.goit.module4.connectors.ConnectorManager;
import ua.goit.module4.connectors.DbType;
import ua.goit.module4.console.InputConsole;

public class StartMenu extends AbstractMenu {

    private static StartMenu instance;

    public static StartMenu getInstance() {
        if (instance == null) {
            instance = new StartMenu();
        }
        return instance;
    }

    private StartMenu() {
        Menu mainMenu = MainMenu.getInstance();

        menuModel = new MenuModel();
        menuModel.put("1", param -> {
            InputConsole.getInstance().setDbConnector(ConnectorManager.connectTo(DbType.POSTGRES));
            InputConsole.getInstance().setCurrentMenu(mainMenu);
            mainMenu.showMenu();
        });
        menuModel.put("2", param -> {
            InputConsole.getInstance().setDbConnector(ConnectorManager.connectTo(DbType.MSSQL));
            InputConsole.getInstance().setCurrentMenu(mainMenu);
            mainMenu.showMenu();
        });
        menuModel.put("exit", param -> System.exit(0));
    }

    @Override
    public void showMenu() {
        String startMenu = """
                
                ******** START MENU ********
                Connect to...              
                1.Postgres-> (command format 1)
                2.MS SQL-> (command format 2)
                EXIT
                ******** ********
                """;
        System.out.println(startMenu);
    }

}

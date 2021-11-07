package ua.goit.module4.console;

import ua.goit.module4.connectors.ConnectorManager;
import ua.goit.module4.connectors.DbType;

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
        menuModel.put("postgres", param -> {
            InputConsole.getInstance().setDbConnector(ConnectorManager.connectTo(DbType.POSTGRES));
            InputConsole.getInstance().setCurrentMenu(mainMenu);
            mainMenu.showMenu();
        });
        menuModel.put("mssql", param -> {
            InputConsole.getInstance().setDbConnector(ConnectorManager.connectTo(DbType.MSSQL));
            InputConsole.getInstance().setCurrentMenu(mainMenu);
            mainMenu.showMenu();
        });
    }

    @Override
    public void showMenu() {
        String startMenu = """
                ******** START MENU ********
                Connect to...              
                1. Postgres
                2. MS SQL
                EXIT
                ******** ********""";
        System.out.println(startMenu);
    }

}

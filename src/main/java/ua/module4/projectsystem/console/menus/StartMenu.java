package ua.module4.projectsystem.console.menus;

import ua.module4.projectsystem.connectors.ConnectorManager;
import ua.module4.projectsystem.connectors.DbType;
import ua.module4.projectsystem.console.InputConsole;
import ua.module4.projectsystem.console.MenuModel;

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
        menuModel.put("exit", param -> System.exit(0));
    }

    @Override
    public void showMenu() {
        String textMenu = """
                
                ******** START MENU ********
                Connect to...              
                1.Postgres-> (command 'postgres')
                2.MS SQL-> (command 'mssql')
                EXIT (command 'exit')
                ******** ********
                """;
        System.out.println(textMenu);
    }

}

package ua.goit.module4.console;

public class MainMenu extends AbstractMenu {

    private static MainMenu instance;

    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu();
        }
        return instance;
    }

    private MainMenu() {

        menuModel = new MenuModel();
        menuModel.put("companies", param -> {
            Menu companyMenu = CompanyMenu.getInstance();
            InputConsole.getInstance().setCurrentMenu(companyMenu);
            companyMenu.showMenu();
        });
        /*menuModel.put("mssql", param -> {
            InputConsole.getInstance().setDbConnector(ConnectorManager.connectTo(DbType.MSSQL));
            InputConsole.getInstance().setCurrentMenu(mainMenu);
            mainMenu.showMenu();
        });*/
    }

    @Override
    public void showMenu() {
        String textMenu = """
                ******** MAIN MENU ********             
                1. Companies
                2. Customers
                3. Skills
                4. Developers
                5. Projects
                EXIT...
                ******** ********""";
        System.out.println(textMenu);
    }
}

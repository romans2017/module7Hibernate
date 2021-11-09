package ua.goit.module4.console.menus;

import ua.goit.module4.console.InputConsole;

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
        menuModel.put("1", param -> {
            CompanyMenu menu = CompanyMenu.getInstance();
            InputConsole.getInstance().setCurrentMenu(menu);
            menu.setPreviousMenu(this);
            menu.showMenu();
        });
        menuModel.put("2", param -> {
            CustomerMenu menu = CustomerMenu.getInstance();
            InputConsole.getInstance().setCurrentMenu(menu);
            menu.setPreviousMenu(this);
            menu.showMenu();
        });
        menuModel.put("3", param -> {
            SkillMenu menu = SkillMenu.getInstance();
            InputConsole.getInstance().setCurrentMenu(menu);
            menu.setPreviousMenu(this);
            menu.showMenu();
        });
        menuModel.put("4", param -> {
            DeveloperMenu menu = DeveloperMenu.getInstance();
            InputConsole.getInstance().setCurrentMenu(menu);
            menu.setPreviousMenu(this);
            menu.showMenu();
        });
        menuModel.put("5", param -> {
            ProjectMenu menu = ProjectMenu.getInstance();
            InputConsole.getInstance().setCurrentMenu(menu);
            menu.setPreviousMenu(this);
            menu.showMenu();
        });
        menuModel.put("exit", param -> System.exit(0));
    }

    @Override
    public void showMenu() {
        String textMenu = """
                
                ******** MAIN MENU ********             
                1.Companies-> (command 1) 
                2.Customers-> (command 2)
                3.Skills-> (command 3)
                4.Developers-> (command 4)
                5.Projects-> (command 5)
                EXIT...
                ******** ********
                """;
        System.out.println(textMenu);
    }
}

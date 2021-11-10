package ua.module4.projectsystem.console.menus;

import ua.module4.projectsystem.console.InputConsole;
import ua.module4.projectsystem.console.MenuModel;

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
            CompanyMenu menu = CompanyMenu.getInstance();
            InputConsole.getInstance().setCurrentMenu(menu);
            menu.setPreviousMenu(this);
            menu.showMenu();
        });
        menuModel.put("customers", param -> {
            CustomerMenu menu = CustomerMenu.getInstance();
            InputConsole.getInstance().setCurrentMenu(menu);
            menu.setPreviousMenu(this);
            menu.showMenu();
        });
        menuModel.put("skills", param -> {
            SkillMenu menu = SkillMenu.getInstance();
            InputConsole.getInstance().setCurrentMenu(menu);
            menu.setPreviousMenu(this);
            menu.showMenu();
        });
        menuModel.put("developers", param -> {
            DeveloperMenu menu = DeveloperMenu.getInstance();
            InputConsole.getInstance().setCurrentMenu(menu);
            menu.setPreviousMenu(this);
            menu.showMenu();
        });
        menuModel.put("projects", param -> {
            ProjectMenu menu = ProjectMenu.getInstance();
            InputConsole.getInstance().setCurrentMenu(menu);
            menu.setPreviousMenu(this);
            menu.showMenu();
        });
        menuModel.put("reports", param -> {
            ReportMenu menu = ReportMenu.getInstance();
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
                1.Companies-> (command 'companies') 
                2.Customers-> (command 'customers')
                3.Skills-> (command 'skills')
                4.Developers-> (command 'developers')
                5.Projects-> (command 'projects')
                6.REPORTS-> (command 'reports')
                EXIT... (command 'exit')
                ******** ********
                """;
        System.out.println(textMenu);
    }
}

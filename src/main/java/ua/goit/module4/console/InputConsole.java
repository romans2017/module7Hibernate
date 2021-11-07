package ua.goit.module4.console;

import ua.goit.module4.connectors.dbcontrollers.DbConnector;

import java.util.Locale;
import java.util.function.Consumer;

public class InputConsole {

    private DbConnector dbConnector;
    private Menu currentMenu;

    private static InputConsole instance;

    public static InputConsole getInstance() {
        if (instance == null) {
            instance = new InputConsole();
        }
        return instance;
    }

    private InputConsole() {
        currentMenu = StartMenu.getInstance();
        currentMenu.showMenu();
    }

    public void processInput(String inputString) {
        //разбиваем на массив с пробелами
        String[] params = inputString.trim().toLowerCase(Locale.ROOT).split("\s+");

        //получаем первое слово. Считаем, что оно команда меню
        String command = params[0];

        //получаем и выполняем команду из меню (если есть). Переопределение меню происходит в команде
        Consumer<String> currentCommand = currentMenu.getMenuModel().get(command);
        if (currentCommand != null) {
            currentCommand.accept(inputString);
        }
    }

    public void setCurrentMenu(Menu menu) {
        currentMenu = menu;
    }

    public void setDbConnector(DbConnector connector) {
        dbConnector = connector;
    }

    public DbConnector getDbConnector() {
        return dbConnector;
    }
}

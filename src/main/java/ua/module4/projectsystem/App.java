package ua.module4.projectsystem;

import ua.module4.projectsystem.console.InputConsole;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args ) {
        InputConsole inputConsole = InputConsole.getInstance();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            inputConsole.processInput(scanner.nextLine());
        }
    }
}

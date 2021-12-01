package ua.module6.projectsystem;

import ua.module6.projectsystem.console.InputConsole;

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

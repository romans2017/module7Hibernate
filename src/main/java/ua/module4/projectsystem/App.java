package ua.module4.projectsystem;

import ua.module4.projectsystem.console.InputConsole;

import java.sql.SQLException;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args ) throws SQLException {
        /*DbConnector connector = ConnectorManager.connectTo(DbType.POSTGRES);

        CompanyQuery companyQuery = CompanyQuery.getInstance(connector);
        CustomerQuery customerQuery = CustomerQuery.getInstance(connector);
        ProjectQuery projectQuery = ProjectQuery.getInstance(connector);
        //System.out.println(companyQuery.add(new Company(23, "sdfsf", "wefrgewrgwer")));
        //System.out.println(companyQuery.update(new Company("qweqwe", "45345345"), 2));
        Map<String, Object> filter = new HashMap<>();
        filter.put("name", "sdfsf");
        //List<Company> list = companyQuery.get(filter);
        List<Company> list = (List<Company>) companyQuery.getAll();
        System.out.println(list.toString());
        List<Customer> list1 = (List<Customer>) customerQuery.getAll();
        System.out.println(list1.toString());
        List<Project> list2 = (List<Project>) projectQuery.getAllAdvanced();
        System.out.println(list2.toString());*/

        //System.out.println(companyQuery.delete(19));
        /*ResultSet rs = null;
        rs = connector.executeStatementQuery("select * from companies", ps-> {});
        while (rs.next()) {
            System.out.println(rs.getString(2));
        }*/
        InputConsole inputConsole = InputConsole.getInstance();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            inputConsole.processInput(scanner.nextLine());
        }
    }
}

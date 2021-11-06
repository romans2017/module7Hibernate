package ua.goit.module4;

import ua.goit.module4.connectors.ConnectorManager;
import ua.goit.module4.connectors.dbcontrollers.DbConnector;
import ua.goit.module4.connectors.DbType;
import ua.goit.module4.models.Company;
import ua.goit.module4.models.Customer;
import ua.goit.module4.models.DbModel;
import ua.goit.module4.queries.CompanyQuery;
import ua.goit.module4.queries.CustomerQuery;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App 
{
    public static void main( String[] args ) throws SQLException {
        DbConnector connector = ConnectorManager.connectTo(DbType.POSTGRES);

        CompanyQuery companyQuery = CompanyQuery.getInstance(connector);
        CustomerQuery customerQuery = CustomerQuery.getInstance(connector);
        //System.out.println(companyQuery.add(new Company(23, "sdfsf", "wefrgewrgwer")));
        //System.out.println(companyQuery.update(new Company("qweqwe", "45345345"), 2));
        Map<String, Object> filter = new HashMap<>();
        filter.put("name", "sdfsf");
        //List<Company> list = companyQuery.get(filter);
        List<Company> list = companyQuery.getAll();
        System.out.println(list.toString());
        List<Customer> list1 = customerQuery.getAll();
        System.out.println(list1.toString());

        //System.out.println(companyQuery.delete(19));
        /*ResultSet rs = null;
        rs = connector.executeStatementQuery("select * from companies", ps-> {});
        while (rs.next()) {
            System.out.println(rs.getString(2));
        }*/
    }
}

package ua.goit.module4.queries;

import ua.goit.module4.connectors.dbcontrollers.DbConnector;
import ua.goit.module4.models.Customer;
import ua.goit.module4.models.DbModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerQuery extends AbstractQuery{
    private static CustomerQuery instance;

    private CustomerQuery(DbConnector dbConnector) {
        super(dbConnector);
    }

    public static CustomerQuery getInstance(DbConnector dbConnector) {
        if (instance == null) {
            instance = new CustomerQuery(dbConnector);
        }
        return instance;
    }

    @Override
    protected String getTableName() {
        return "customers";
    }

    @Override
    protected Class<? extends DbModel> getTableClass() {
        return Customer.class;
    }

    @Override
    public List<Customer> get(Map<String, Object> simpleFilter) {
        ResultSet resultSet = read(simpleFilter);
        List<Customer> list = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("name"));
                customer.setCountry(resultSet.getString("country"));
                list.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Customer> getAll() {
        return get(new HashMap<>());
    }
}

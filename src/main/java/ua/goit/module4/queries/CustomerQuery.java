package ua.goit.module4.queries;

import ua.goit.module4.connectors.dbcontrollers.DbConnector;
import ua.goit.module4.models.Customer;
import ua.goit.module4.models.DbModel;
import ua.goit.module4.models.ModelsList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerQuery extends AbstractQuery {
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
    protected ModelsList normalizeSqlResponse(ResultSet resultSet) throws SQLException {
        ModelsList list = new ModelsList();

        while (resultSet.next()) {
            Customer customer = new Customer();
            customer.setId(resultSet.getInt("id"));
            customer.setName(resultSet.getString("name"));
            customer.setCountry(resultSet.getString("country"));
            list.add(customer);
        }
        resultSet.close();
        return list;
    }
}

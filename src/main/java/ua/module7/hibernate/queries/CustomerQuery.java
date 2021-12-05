package ua.module7.hibernate.queries;

import ua.module7.hibernate.connectors.dbcontrollers.DbConnector;
import ua.module7.hibernate.models.Customer;
import ua.module7.hibernate.models.DbModel;
import ua.module7.hibernate.models.ModelsList;

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
    public String getTableName() {
        return "customers";
    }

    @Override
    protected Class<? extends DbModel> getTableClass() {
        return Customer.class;
    }

    @Override
    public ModelsList normalizeSqlResponse(ResultSet resultSet) throws SQLException {
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

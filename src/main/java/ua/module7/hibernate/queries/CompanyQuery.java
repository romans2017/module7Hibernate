package ua.module7.hibernate.queries;

import ua.module7.hibernate.connectors.dbcontrollers.DbConnector;
import ua.module7.hibernate.models.Company;
import ua.module7.hibernate.models.DbModel;
import ua.module7.hibernate.models.ModelsList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyQuery extends AbstractQuery {
    private static CompanyQuery instance;

    private CompanyQuery(DbConnector dbConnector) {
        super(dbConnector);
    }

    public static CompanyQuery getInstance(DbConnector dbConnector) {
        if (instance == null) {
            instance = new CompanyQuery(dbConnector);
        }
        return instance;
    }

    @Override
    public String getTableName() {
        return "companies";
    }

    @Override
    protected Class<? extends DbModel> getTableClass() {
        return Company.class;
    }

    @Override
    public ModelsList normalizeSqlResponse(ResultSet resultSet) throws SQLException {

        ModelsList list = new ModelsList();
        while (resultSet.next()) {
            Company company = new Company();
            company.setId(resultSet.getInt("id"));
            company.setName(resultSet.getString("name"));
            company.setCountry(resultSet.getString("country"));
            list.add(company);
        }
        resultSet.close();
        return list;
    }

}

package ua.goit.module4.queries;

import ua.goit.module4.connectors.dbcontrollers.DbConnector;
import ua.goit.module4.models.Company;
import ua.goit.module4.models.DbModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    protected String getTableName() {
        return "companies";
    }

    @Override
    protected Class<? extends DbModel> getTableClass() {
        return Company.class;
    }

    @Override
    protected List<? extends DbModel> normalizeSqlResponse(ResultSet resultSet) throws SQLException {

        List<Company> list = new ArrayList<>();
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

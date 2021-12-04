package ua.module6.projectsystem.queries;

import ua.module6.projectsystem.connectors.dbcontrollers.DbConnector;
import ua.module6.projectsystem.models.Company;
import ua.module6.projectsystem.models.DbModel;
import ua.module6.projectsystem.models.ModelsList;

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

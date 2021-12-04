package ua.module6.projectsystem.queries;

import ua.module6.projectsystem.connectors.dbcontrollers.DbConnector;
import ua.module6.projectsystem.models.DbModel;
import ua.module6.projectsystem.models.ModelsList;
import ua.module6.projectsystem.models.ReportDevelopers;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportDevelopersJavaQuery extends AbstractQuery  {

    private static ReportDevelopersJavaQuery instance;

    private ReportDevelopersJavaQuery(DbConnector dbConnector) {
        super(dbConnector);
    }

    public static ReportDevelopersJavaQuery getInstance(DbConnector dbConnector) {
        if (instance == null) {
            instance = new ReportDevelopersJavaQuery(dbConnector);
        }
        return instance;
    }

    @Override
    public String getTableName() {
        return "developers_skills";
    }

    @Override
    protected Class<? extends DbModel> getTableClass() {
        return ReportDevelopers.class;
    }

    @Override
    public ModelsList normalizeSqlResponse(ResultSet resultSet) throws SQLException {

        ModelsList list = new ModelsList();
        while (resultSet.next()) {
            ReportDevelopers reportDevelopers = new ReportDevelopers();
            reportDevelopers.setDeveloper_id(resultSet.getInt("developer_id"));
            reportDevelopers.setDeveloper_name(resultSet.getString("developer_name"));
            reportDevelopers.setAge(resultSet.getInt("age"));
            reportDevelopers.setSalary(resultSet.getInt("salary"));
            reportDevelopers.setLanguage(resultSet.getString("language"));
            reportDevelopers.setLevel(resultSet.getString("level"));
            list.add(reportDevelopers);
        }
        resultSet.close();
        return list;
    }

    public StringBuilder getAdvancedMainRequest() {

        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder
                .append("SELECT " +
                        "developers.name as developer_name, " +
                        "developers.salary as salary, " +
                        "developers.age as age, " +
                        "developers.id as developer_id, " +
                        "skills.language as language, " +
                        "skills.level as level")
                .append(" FROM ")
                .append(getTableName())
                .append(" JOIN developers ON ")
                .append(getTableName())
                .append(".developer_id = developers.id")
                .append(" JOIN skills ON ")
                .append(getTableName())
                .append(".skill_id = skills.id")
                .append(" WHERE skills.language = 'JAVA' or skills.language = 'Java' or skills.language = 'java'");

    }
}

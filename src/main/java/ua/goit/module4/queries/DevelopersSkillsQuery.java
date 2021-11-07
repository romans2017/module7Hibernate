package ua.goit.module4.queries;

import ua.goit.module4.connectors.dbcontrollers.DbConnector;
import ua.goit.module4.models.DbModel;
import ua.goit.module4.models.DevelopersSkills;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DevelopersSkillsQuery extends AbstractQuery {

    private static DevelopersSkillsQuery instance;

    private DevelopersSkillsQuery(DbConnector dbConnector) {
        super(dbConnector);
    }

    public static DevelopersSkillsQuery getInstance(DbConnector dbConnector) {
        if (instance == null) {
            instance = new DevelopersSkillsQuery(dbConnector);
        }
        return instance;
    }

    @Override
    protected String getTableName() {
        return "developers_skills";
    }

    @Override
    protected Class<? extends DbModel> getTableClass() {
        return DevelopersSkills.class;
    }

    @Override
    protected List<? extends DbModel> normalizeSqlResponse(ResultSet resultSet) throws SQLException {
        List<DevelopersSkills> list = new ArrayList<>();

        while (resultSet.next()) {
            DevelopersSkills developersSkills = new DevelopersSkills();
            developersSkills.setId(resultSet.getInt("id"));
            developersSkills.setSkill_id(resultSet.getInt("skill_id"));
            developersSkills.setDeveloper_id(resultSet.getInt("company_id"));
            list.add(developersSkills);
        }
        resultSet.close();
        return list;
    }

    @Override
    public StringBuilder getAdvancedMainRequest() {

        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append("SELECT ")
                .append(getTableName())
                .append(".*, ")
                .append("companies.name as company_name, skills.name as skill_name")
                .append(" FROM ")
                .append(getTableName())
                .append(" JOIN companies ON ")
                .append(getTableName())
                .append(".company_id = companies.id")
                .append(" JOIN skills ON ")
                .append(getTableName())
                .append(".skill_id = skills.id");

    }

}

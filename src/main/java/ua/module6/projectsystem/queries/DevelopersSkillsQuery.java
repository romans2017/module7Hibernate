package ua.module6.projectsystem.queries;

import ua.module6.projectsystem.connectors.dbcontrollers.DbConnector;
import ua.module6.projectsystem.models.DbModel;
import ua.module6.projectsystem.models.DevelopersSkills;
import ua.module6.projectsystem.models.ModelsList;

import java.sql.ResultSet;
import java.sql.SQLException;

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
    protected ModelsList normalizeSqlResponse(ResultSet resultSet) throws SQLException {
        ModelsList list = new ModelsList();

        while (resultSet.next()) {
            DevelopersSkills developersSkills = new DevelopersSkills();
            developersSkills.setId(resultSet.getInt("id"));
            developersSkills.setDeveloper_id(resultSet.getInt("developer_id"));
            developersSkills.setSkill_id(resultSet.getInt("skill_id"));
            try {
                developersSkills.setDeveloper_name(resultSet.getString("developer_name"));
                developersSkills.setSkill_language(resultSet.getString("skill_language"));
                developersSkills.setSkill_level(resultSet.getString("skill_level"));
            } catch (SQLException ignored) {
            }
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
                .append("developers.name as developer_name, skills.language as skill_language, skills.level as skill_level")
                .append(" FROM ")
                .append(getTableName())
                .append(" JOIN developers ON ")
                .append(getTableName())
                .append(".developer_id = developers.id")
                .append(" JOIN skills ON ")
                .append(getTableName())
                .append(".skill_id = skills.id");

    }

}

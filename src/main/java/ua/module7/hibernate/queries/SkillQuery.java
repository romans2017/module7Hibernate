package ua.module7.hibernate.queries;

import ua.module7.hibernate.connectors.dbcontrollers.DbConnector;
import ua.module7.hibernate.models.DbModel;
import ua.module7.hibernate.models.ModelsList;
import ua.module7.hibernate.models.Skill;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SkillQuery extends AbstractQuery {

    private static SkillQuery instance;

    private SkillQuery(DbConnector dbConnector) {
        super(dbConnector);
    }

    public static SkillQuery getInstance(DbConnector dbConnector) {
        if (instance == null) {
            instance = new SkillQuery(dbConnector);
        }
        return instance;
    }

    @Override
    public String getTableName() {
        return "skills";
    }

    @Override
    protected Class<? extends DbModel> getTableClass() {
        return Skill.class;
    }

    @Override
    public ModelsList normalizeSqlResponse(ResultSet resultSet) throws SQLException {
        ModelsList list = new ModelsList();

        while (resultSet.next()) {
            Skill skill = new Skill();
            skill.setId(resultSet.getInt("id"));
            skill.setLanguage(resultSet.getString("language"));
            skill.setLevel(resultSet.getString("level"));
            list.add(skill);
        }
        resultSet.close();
        return list;
    }

}

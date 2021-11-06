package ua.goit.module4.queries;

import ua.goit.module4.connectors.dbcontrollers.DbConnector;
import ua.goit.module4.models.DbModel;
import ua.goit.module4.models.DevelopersSkills;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<DevelopersSkills> get(Map<String, Object> simpleFilter) {
        ResultSet resultSet = read(simpleFilter);
        List<DevelopersSkills> list = new ArrayList<>();

        try {
            while (resultSet.next()) {
                DevelopersSkills developersSkills = new DevelopersSkills();
                developersSkills.setId(resultSet.getInt("id"));
                developersSkills.setSkill_id(resultSet.getInt("skill_id"));
                developersSkills.setDeveloper_id(resultSet.getInt("company_id"));
                list.add(developersSkills);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<DevelopersSkills> getAll() {
        return get(new HashMap<>());
    }

}

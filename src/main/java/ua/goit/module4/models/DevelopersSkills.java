package ua.goit.module4.models;

public class DevelopersSkills extends AbstractModel {

    private Integer id;
    private Integer developer_id;
    private Integer skill_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeveloper_id() {
        return developer_id;
    }

    public void setDeveloper_id(Integer developer_id) {
        this.developer_id = developer_id;
    }

    public Integer getSkill_id() {
        return skill_id;
    }

    public void setSkill_id(Integer skill_id) {
        this.skill_id = skill_id;
    }

    @Override
    public String toString() {
        return "DevelopersSkills{" +
                "id=" + id +
                ", developer_id=" + developer_id +
                ", skill_id=" + skill_id +
                '}';
    }
}

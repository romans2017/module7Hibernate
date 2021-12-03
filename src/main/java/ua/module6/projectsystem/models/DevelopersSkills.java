package ua.module6.projectsystem.models;

public class DevelopersSkills extends AbstractModel {

    private Integer developer_id;
    private String developer_name;
    private Integer skill_id;
    private String skill_language;
    private String skill_level;

    public DevelopersSkills() {
    }

    public DevelopersSkills(String[] params) throws ArrayIndexOutOfBoundsException, NumberFormatException {
        this.developer_id = Integer.parseInt(params[1].trim());
        this.skill_id = Integer.parseInt(params[2].trim());
    }

    public DevelopersSkills(DevelopersSkills currentDbModel, String[] params) throws ArrayIndexOutOfBoundsException, NumberFormatException {
        this.developer_id = params[2].isBlank() ? currentDbModel.getDeveloper_id() : Integer.parseInt(params[2].trim());
        this.skill_id = params[3].isBlank() ? currentDbModel.getSkill_id() : Integer.parseInt(params[3].trim());
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

    public String getDeveloper_name() {
        return developer_name;
    }

    public void setDeveloper_name(String developer_name) {
        this.developer_name = developer_name;
    }

    public String getSkill_language() {
        return skill_language;
    }

    public void setSkill_language(String skill_language) {
        this.skill_language = skill_language;
    }

    public String getSkill_level() {
        return skill_level;
    }

    public void setSkill_level(String skill_level) {
        this.skill_level = skill_level;
    }

    @Override
    public String toString() {
        return "DevelopersSkills{" +
                ", developer_id=" + developer_id +
                ", developer_name='" + developer_name + '\'' +
                ", skill_id=" + skill_id +
                ", skill_language='" + skill_language + '\'' +
                "} ";
    }
}

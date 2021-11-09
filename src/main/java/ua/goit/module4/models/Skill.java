package ua.goit.module4.models;

public class Skill extends AbstractModel {

    private Integer id;
    private String language;
    private String level;

    public Skill() {
    }

    public Skill(String[] params) throws ArrayIndexOutOfBoundsException {
        this.language = params[1].trim();
        this.level = params[2].trim();
    }

    public Skill(Skill currentDbModel, String[] params) throws ArrayIndexOutOfBoundsException {
        this.language = params[2].isBlank() ? currentDbModel.getLanguage() : params[2].trim();
        this.level = params[3].isBlank() ? currentDbModel.getLevel() : params[3].trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", language='" + language + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}

package ua.module6.projectsystem.models;

public class ReportDevelopers extends AbstractModel{

    private Integer developer_id;
    private String developer_name;
    private Integer age;
    private Integer salary;
    private String language;
    private String level;

    public void setDeveloper_name(String developer_name) {
        this.developer_name = developer_name;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setDeveloper_id(Integer developer_id) {
        this.developer_id = developer_id;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getDeveloper_id() {
        return developer_id;
    }

    public String getDeveloper_name() {
        return developer_name;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getSalary() {
        return salary;
    }

    public String getLanguage() {
        return language;
    }

    public String getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "ReportDevelopersJava{" +
                "developer_id=" + developer_id +
                ", developer_name='" + developer_name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", language='" + language + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}

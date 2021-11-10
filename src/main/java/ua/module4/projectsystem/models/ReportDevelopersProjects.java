package ua.module4.projectsystem.models;

public class ReportDevelopersProjects extends AbstractModel{

    private Integer project_id;
    private String project_name;
    private Integer developer_id;
    private String developer_name;
    private Integer age;
    private Integer salary;

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public void setDeveloper_name(String developer_name) {
        this.developer_name = developer_name;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    public void setDeveloper_id(Integer developer_id) {
        this.developer_id = developer_id;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ReportDevelopersProjects{" +
                "project_name='" + project_name + '\'' +
                ", developer_id='" + developer_id + '\'' +
                ", developer_name='" + developer_name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}

package ua.module6.projectsystem.models;

public class ReportDevelopersSalary extends AbstractModel{

    private Integer project_id;
    private String project_name;
    private String developer_name;
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

    @Override
    public String toString() {
        return "ReportDevelopersSalary{" +
                "project_name='" + project_name + '\'' +
                ", developer_name='" + developer_name + '\'' +
                ", salary=" + salary +
                '}';
    }
}

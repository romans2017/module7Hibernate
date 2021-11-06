package ua.goit.module4.models;

public class DevelopersProjects extends AbstractModel {

    private Integer id;
    private Integer developer_id;
    private Integer project_id;

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

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    @Override
    public String toString() {
        return "DevelopersProjects{" +
                "id=" + id +
                ", developer_id=" + developer_id +
                ", project_id='" + project_id + '\'' +
                '}';
    }
}

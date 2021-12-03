package ua.module6.projectsystem.models;

public class DevelopersProjects extends AbstractModel {

    private Integer developer_id;
    private String developer_name;
    private Integer project_id;
    private String project_name;

    public DevelopersProjects() {
        developer_id = 0;
        developer_name = "";
        project_id = 0;
        project_name = "";
    }

    public DevelopersProjects(String[] params) throws ArrayIndexOutOfBoundsException, NumberFormatException {
        this.developer_id = Integer.parseInt(params[1].trim());
        this.project_id = Integer.parseInt(params[2].trim());
    }

    public DevelopersProjects(DevelopersProjects currentDbModel, String[] params) throws ArrayIndexOutOfBoundsException, NumberFormatException {
        this.developer_id = params[2].isBlank() ? currentDbModel.getDeveloper_id() : Integer.parseInt(params[2].trim());
        this.project_id = params[3].isBlank() ? currentDbModel.getProject_id() : Integer.parseInt(params[3].trim());
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

    public String getDeveloper_name() {
        return developer_name;
    }

    public void setDeveloper_name(String developer_name) {
        this.developer_name = developer_name;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    @Override
    public String toString() {
        return "DevelopersProjects{" +
                ", developer_id=" + developer_id +
                ", developer_name='" + developer_name + '\'' +
                ", project_id=" + project_id +
                ", project_name='" + project_name + '\'' +
                '}';
    }
}

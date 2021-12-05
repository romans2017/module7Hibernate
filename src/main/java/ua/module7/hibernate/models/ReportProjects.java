package ua.module7.hibernate.models;

import java.util.Date;

public class ReportProjects extends AbstractModel{

    private Integer project_id;
    private String project_name;
    private Date creation_date;
    private Integer number_developers;

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public void setNumber_developers(Integer number_developers) {
        this.number_developers = number_developers;
    }

    public Integer getProject_id() {
        return project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public Integer getNumber_developers() {
        return number_developers;
    }

    @Override
    public String toString() {
        return "ReportProjects{" +
                "creation_date=" + creation_date +
                ", project_name='" + project_name + '\'' +
                ", number_developers=" + number_developers +
                '}';
    }
}

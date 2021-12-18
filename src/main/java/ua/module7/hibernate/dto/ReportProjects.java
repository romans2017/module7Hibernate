package ua.module7.hibernate.dto;

import java.util.Date;

public class ReportProjects {

    private Integer projectId;
    private String projectName;
    private Date creationDate;
    private Integer numberDevelopers;

    public ReportProjects(Integer projectId, String projectName, Date creationDate, Integer numberDevelopers) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.creationDate = creationDate;
        this.numberDevelopers = numberDevelopers;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getNumberDevelopers() {
        return numberDevelopers;
    }

    public void setNumberDevelopers(Integer numberDevelopers) {
        this.numberDevelopers = numberDevelopers;
    }
}

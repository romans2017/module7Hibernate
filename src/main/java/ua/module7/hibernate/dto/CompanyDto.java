package ua.module7.hibernate.dto;

import java.util.HashSet;
import java.util.Set;

public class CompanyDto implements Dto {

    private Integer id;
    private String name;
    private String country;
    private Set<ProjectDto> projects = new HashSet<>();
    private Set<DeveloperDto> developers = new HashSet<>();

    public CompanyDto() {
    }

    public CompanyDto(Integer id, String name, String country, Set<ProjectDto> projects, Set<DeveloperDto> developers) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.projects = projects;
        this.developers = developers;
    }

    public String getCountry() {
        return country;
    }

    public CompanyDto setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getName() {
        return name;
    }

    public CompanyDto setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public CompanyDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public Set<ProjectDto> getProjects() {
        return projects;
    }

    public CompanyDto setProjects(Set<ProjectDto> projects) {
        this.projects = projects;
        return this;
    }

    public Set<DeveloperDto> getDevelopers() {
        return developers;
    }

    public CompanyDto setDevelopers(Set<DeveloperDto> developers) {
        this.developers = developers;
        return this;
    }

    @Override
    public CompanyDto initEmpty() {
        return setCountry("").setId(0).setName("");
    }
}
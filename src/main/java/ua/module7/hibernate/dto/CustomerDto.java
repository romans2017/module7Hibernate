package ua.module7.hibernate.dto;

import java.util.HashSet;
import java.util.Set;

public class CustomerDto implements Dto {

    private Integer id;
    private String name;
    private String country;
    private Set<ProjectDto> projects = new HashSet<>();

    public String getCountry() {
        return country;
    }

    public CustomerDto setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getName() {
        return name;
    }

    public CustomerDto setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public CustomerDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public Set<ProjectDto> getProjects() {
        return projects;
    }

    public CustomerDto setProjects(Set<ProjectDto> projects) {
        this.projects = projects;
        return this;
    }

    @Override
    public Dto initEmpty() {
        return setId(0).setName("").setCountry("");
    }
}
package ua.module7.hibernate.dto;

import java.util.HashSet;
import java.util.Set;

public class DeveloperDto implements Dto {

    private Integer id;
    private Set<CompanyDto> companies = new HashSet<>();
    private String name;
    private Integer age;
    private Integer salary;
    private Set<ProjectDto> projects = new HashSet<>();
    private Set<SkillDto> skills = new HashSet<>();

    public Integer getSalary() {
        return salary;
    }

    public DeveloperDto setSalary(Integer salary) {
        this.salary = salary;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public DeveloperDto setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getName() {
        return name;
    }

    public DeveloperDto setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public DeveloperDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public Set<CompanyDto> getCompanies() {
        return companies;
    }

    public DeveloperDto setCompanies(Set<CompanyDto> companies) {
        this.companies = companies;
        return this;
    }

    public Set<SkillDto> getSkills() {
        return skills;
    }

    public DeveloperDto setSkills(Set<SkillDto> skills) {
        this.skills = skills;
        return this;
    }

    public Set<ProjectDto> getProjects() {
        return projects;
    }

    public DeveloperDto setProjects(Set<ProjectDto> projects) {
        this.projects = projects;
        return this;
    }

    @Override
    public Dto initEmpty() {
        return setId(0).setName("").setAge(0).setSalary(0);
    }
}
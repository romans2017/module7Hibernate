package ua.module7.hibernate.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ProjectDto implements Dto {

    private Integer id;
    private Set<CompanyDto> companies = new HashSet<>();
    private Set<CustomerDto> customers = new HashSet<>();
    private String name;
    private String description;
    private Integer cost;
    private Date creationDate;
    private Set<DeveloperDto> developers = new HashSet<>();

    public Date getCreationDate() {
        return creationDate;
    }

    public ProjectDto setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public Integer getCost() {
        return cost;
    }

    public ProjectDto setCost(Integer cost) {
        this.cost = cost;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProjectDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProjectDto setName(String name) {
        this.name = name;
        return this;
    }

    public Set<CompanyDto> getCompanies() {
        return companies;
    }

    public ProjectDto setCompanies(Set<CompanyDto> companies) {
        this.companies = companies;
        return this;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public ProjectDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public Set<DeveloperDto> getDevelopers() {
        return developers;
    }

    public ProjectDto setDevelopers(Set<DeveloperDto> developers) {
        this.developers = developers;
        return this;
    }

    public Set<CustomerDto> getCustomers() {
        return customers;
    }

    public ProjectDto setCustomers(Set<CustomerDto> customers) {
        this.customers = customers;
        return this;
    }

    @Override
    public Dto initEmpty() {
        return setId(0).setName("").setCost(0).setCreationDate(new Date());
    }
}
package ua.module7.hibernate.pojo;

import javax.persistence.*;
import java.text.DateFormat;
import java.util.*;

@Entity
@Table(name = "projects")
@NamedEntityGraph(name = "ProjectGraph",
        attributeNodes = {@NamedAttributeNode("company"), @NamedAttributeNode("customer"), @NamedAttributeNode("developers")})
public class Project implements Pojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "companies_projects",
            joinColumns = { @JoinColumn(name = "project_id") },
            inverseJoinColumns = { @JoinColumn(name = "company_id") }
    )
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "customers_projects",
            joinColumns = { @JoinColumn(name = "project_id") },
            inverseJoinColumns = { @JoinColumn(name = "customer_id") }
    )
    private Customer customer;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "cost", nullable = false)
    private Integer cost;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @ManyToMany(mappedBy = "projects", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Developer> developers = new TreeSet<>();

    @Override
    public Pojo initEmpty() {
        return setId(0).setName("").setCost(0).setCreationDate(new Date()).setDescription("");
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getCreationDateFormatted() {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
        return dateFormat.format(Optional.ofNullable(getCreationDate()).orElse(new Date()));
    }

    public Project setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public Integer getCost() {
        return cost;
    }

    public Project setCost(Integer cost) {
        this.cost = cost;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Project setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public Project setName(String name) {
        this.name = name;
        return this;
    }

    public Company getCompany() {
        return company;
    }

    public Project setCompany(Company company) {
        this.company = company;
        return this;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public Project setId(Integer id) {
        this.id = id;
        return this;
    }

    public Set<Developer> getDevelopers() {
        return developers;
    }

    public Project setDevelopers(Set<Developer> developers) {
        this.developers = developers;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Project setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public Project addCustomer(Customer customer) {
        this.customer = customer;
        customer.getProjects().add(this);
        return this;
    }

    public Project removeCustomer(Customer customer) {
        this.customer = null;
        customer.getProjects().remove(this);
        return this;
    }

    public Project addCompany(Company company) {
        this.company = company;
        company.getProjects().add(this);
        return this;
    }

    public Project removeCompany(Company company) {
        this.company = null;
        company.getProjects().remove(this);
        return this;
    }

    public Project addDeveloper(Developer developer) {
        this.developers.add(developer);
        developer.getProjects().add(this);
        return this;
    }

    public Project removeDeveloper(Developer developer) {
        this.developers.remove(developer);
        developer.getProjects().remove(this);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (getId() != null ? !getId().equals(project.getId()) : project.getId() != null) return false;
        if (!getName().equals(project.getName())) return false;
        if (!getDescription().equals(project.getDescription())) return false;
        if (!getCost().equals(project.getCost())) return false;
        return getCreationDate().equals(project.getCreationDate());
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getName().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getCost().hashCode();
        result = 31 * result + getCreationDate().hashCode();
        return result;
    }

    @Override
    public int compareTo(Pojo o) {
        return this.getId() - o.getId();
    }
}
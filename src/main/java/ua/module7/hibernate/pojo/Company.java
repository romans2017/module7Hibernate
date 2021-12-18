package ua.module7.hibernate.pojo;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "companies")
@NamedEntityGraph(name = "CompanyGraph",
        attributeNodes = {@NamedAttributeNode("projects"), @NamedAttributeNode("developers")})
public class Company implements Pojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "country", length = 100)
    private String country;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<Project> projects = new TreeSet<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<Developer> developers = new TreeSet<>();

    public String getCountry() {
        return country;
    }

    public Company setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getName() {
        return name;
    }

    public Company setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Pojo initEmpty() {
        return setCountry("").setId(0).setName("");
    }

    public Company setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public Company setProjects(Set<Project> projects) {
        this.projects = projects;
        return this;
    }

    public Set<Developer> getDevelopers() {
        return developers;
    }

    public Company setDevelopers(Set<Developer> developers) {
        this.developers = developers;
        return this;
    }

    public Company addProject(Project project) {
        this.projects.add(project);
        project.setCompany(this);
        return this;
    }

    public Company removeProject(Project project) {
        this.projects.remove(project);
        project.setCompany(null);
        return this;
    }

    public Company addDeveloper(Developer developer) {
        this.developers.add(developer);
        developer.setCompany(this);
        return this;
    }

    public Company removeDeveloper(Developer developer) {
        this.developers.remove(developer);
        developer.setCompany(null);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (getId() != null ? !getId().equals(company.getId()) : company.getId() != null) return false;
        if (!getName().equals(company.getName())) return false;
        return getCountry().equals(company.getCountry());
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getName().hashCode();
        result = 31 * result + getCountry().hashCode();
        return result;
    }

    @Override
    public int compareTo(Pojo o) {
        return this.getId() - o.getId();
    }
}
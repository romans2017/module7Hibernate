package ua.module7.hibernate.pojo;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "developers")
@NamedEntityGraph(name = "DeveloperGraph",
        attributeNodes = {@NamedAttributeNode("company"), @NamedAttributeNode("projects"), @NamedAttributeNode("skills")})
public class Developer implements Pojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "companies_developers",
            joinColumns = {@JoinColumn(name = "developer_id")},
            inverseJoinColumns = {@JoinColumn(name = "company_id")}
    )
    private Company company;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "salary", nullable = false)
    private Integer salary;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "developers_projects",
            joinColumns = {@JoinColumn(name = "developer_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")}
    )
    private Set<Project> projects = new TreeSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "developers_skills",
            joinColumns = {@JoinColumn(name = "developer_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")}
    )
    private Set<Skill> skills = new TreeSet<>();

    @Override
    public Pojo initEmpty() {
        return setId(0).setName("").setAge(0).setSalary(0);
    }

    public Integer getSalary() {
        return salary;
    }

    public Developer setSalary(Integer salary) {
        this.salary = salary;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Developer setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getName() {
        return name;
    }

    public Developer setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public Developer setId(Integer id) {
        this.id = id;
        return this;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public Developer setSkills(Set<Skill> skills) {
        this.skills = skills;
        return this;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public Developer setProjects(Set<Project> projects) {
        this.projects = projects;
        return this;
    }

    public Company getCompany() {
        return company;
    }

    public Developer setCompany(Company company) {
        this.company = company;
        return this;
    }

    public Developer addCompany(Company company) {
        this.company = company;
        company.getDevelopers().add(this);
        return this;
    }

    public Developer removeCompany(Company company) {
        company.getDevelopers().remove(this);
        this.company = null;
        return this;
    }

    public Developer addProject(Project project) {
        this.projects.add(project);
        project.getDevelopers().add(this);
        return this;
    }

    public Developer removeProject(Project project) {
        this.projects.remove(project);
        project.getDevelopers().remove(this);
        return this;
    }

    public Developer addSkill(Skill skill) {
        this.skills.add(skill);
        skill.getDevelopers().add(this);
        return this;
    }

    public Developer removeSkill(Skill skill) {
        this.skills.remove(skill);
        skill.getDevelopers().remove(this);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Developer developer = (Developer) o;

        if (getId() != null ? !getId().equals(developer.getId()) : developer.getId() != null) return false;
        if (!getName().equals(developer.getName())) return false;
        if (!getAge().equals(developer.getAge())) return false;
        return getSalary().equals(developer.getSalary());
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getName().hashCode();
        result = 31 * result + getAge().hashCode();
        result = 31 * result + getSalary().hashCode();
        return result;
    }

    @Override
    public int compareTo(Pojo o) {
        return this.getId() - o.getId();
    }
}
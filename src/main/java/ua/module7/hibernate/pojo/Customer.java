package ua.module7.hibernate.pojo;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "customers")
@NamedEntityGraph(name = "CustomerGraph",
        attributeNodes = @NamedAttributeNode("projects"))
public class Customer implements Pojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "country", length = 100)
    private String country;

    @OneToMany(mappedBy = "customer")
    private Set<Project> projects = new TreeSet<>();

    @Override
    public Pojo initEmpty() {
        return setId(0).setName("").setCountry("");
    }

    public String getCountry() {
        return country;
    }

    public Customer setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public Customer setId(Integer id) {
        this.id = id;
        return this;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public Customer setProjects(Set<Project> projects) {
        this.projects = projects;
        return this;
    }

    public Customer addProject(Project project) {
        this.projects.add(project);
        project.setCustomer(this);
        return this;
    }

    public Customer removeProject(Project project) {
        this.projects.remove(project);
        project.setCustomer(null);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (getId() != null ? !getId().equals(customer.getId()) : customer.getId() != null) return false;
        if (!getName().equals(customer.getName())) return false;
        return getCountry().equals(customer.getCountry());
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
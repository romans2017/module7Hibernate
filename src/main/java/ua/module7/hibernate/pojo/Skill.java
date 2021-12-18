package ua.module7.hibernate.pojo;

import javax.persistence.*;
import java.util.TreeSet;
import java.util.Set;

@Entity
@Table(name = "skills")
@NamedEntityGraph(name = "SkillGraph",
        attributeNodes = @NamedAttributeNode("developers"))
public class Skill implements Pojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "language", nullable = false, length = 50)
    private String language;

    @Column(name = "level", nullable = false, length = 30)
    private String level;

    //@ManyToMany(mappedBy = "skills", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @ManyToMany(mappedBy = "skills")
    private Set<Developer> developers = new TreeSet<>();

    @Override
    public Pojo initEmpty() {
        return setId(0).setLanguage("").setLevel("");
    }

    public String getLevel() {
        return level;
    }

    public Skill setLevel(String level) {
        this.level = level;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public Skill setLanguage(String language) {
        this.language = language;
        return this;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public Skill setId(Integer id) {
        this.id = id;
        return this;
    }

    public Set<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<Developer> developers) {
        this.developers = developers;
    }

    public Skill addDeveloper(Developer developer) {
        this.developers.add(developer);
        developer.getSkills().add(this);
        return this;
    }

    public Skill removeDeveloper(Developer developer) {
        this.developers.remove(developer);
        developer.getSkills().remove(this);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Skill skill = (Skill) o;

        if (getId() != null ? !getId().equals(skill.getId()) : skill.getId() != null) return false;
        if (!getLanguage().equals(skill.getLanguage())) return false;
        return getLevel().equals(skill.getLevel());
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getLanguage().hashCode();
        result = 31 * result + getLevel().hashCode();
        return result;
    }

    @Override
    public int compareTo(Pojo o) {
        return this.getId() - o.getId();
    }
}
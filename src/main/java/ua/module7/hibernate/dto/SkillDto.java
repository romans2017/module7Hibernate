package ua.module7.hibernate.dto;

import java.util.HashSet;
import java.util.Set;

public class SkillDto implements Dto {

    private Integer id;
    private String language;
    private String level;
    private Set<DeveloperDto> developers = new HashSet<>();

    public String getLevel() {
        return level;
    }

    public SkillDto setLevel(String level) {
        this.level = level;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public SkillDto setLanguage(String language) {
        this.language = language;
        return this;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public SkillDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public Set<DeveloperDto> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<DeveloperDto> developers) {
        this.developers = developers;
    }

    @Override
    public Dto initEmpty() {
        return setId(0).setLanguage("").setLevel("");
    }
}
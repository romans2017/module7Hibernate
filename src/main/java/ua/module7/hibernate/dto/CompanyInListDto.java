package ua.module7.hibernate.dto;

public class CompanyInListDto implements Dto {

    private Integer id;
    private String name;
    private String country;

    public CompanyInListDto() {
    }

    public CompanyInListDto(Integer id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public CompanyInListDto setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getName() {
        return name;
    }

    public CompanyInListDto setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Dto initEmpty() {
        return setCountry("").setId(0).setName("");
    }

    @Override
    public Integer getId() {
        return id;
    }

    public CompanyInListDto setId(Integer id) {
        this.id = id;
        return this;
    }
}
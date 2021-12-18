package ua.module7.hibernate.models;

public class Company extends AbstractModel {

    private Integer id;
    private String name;
    private String country;

    public Company() {
    }

    public Company(Integer id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

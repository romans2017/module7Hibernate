package ua.goit.module4.models;

public class Company extends AbstractModel {

    private Integer id;
    private String name;
    private String country;

    public Company() {
    }

    public Company(String name, String country) {
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

    public void setId(int id) {
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
                ", country='" + country + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

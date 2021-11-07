package ua.goit.module4.models;

public class Company extends AbstractModel {

    private Integer id;
    private String name;
    private String country;

    public Company(){}

    public Company(String[] params) {
        this.name = params[1].trim();
        this.country = params[2].trim();
    }

    public Company(Company currentCompany, String[] params) {
        this.name = params[2].isBlank() ? currentCompany.getName() : params[2].trim();
        this.country = params[3].isBlank() ? currentCompany.getCountry() : params[3].trim();
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

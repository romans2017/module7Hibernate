package ua.module6.projectsystem.models;

public class Company extends AbstractModel {

    private Integer id;
    private String name;
    private String country;

    public Company() {
        id = 0;
        name = "";
        country = "";
    }

    public Company(String[] params) throws ArrayIndexOutOfBoundsException {
        this.name = params[1].trim();
        this.country = params[2].trim();
    }

    public Company(Company currentDbModel, String[] params) throws ArrayIndexOutOfBoundsException {
        this.name = params[2].isBlank() ? currentDbModel.getName() : params[2].trim();
        this.country = params[3].isBlank() ? currentDbModel.getCountry() : params[3].trim();
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

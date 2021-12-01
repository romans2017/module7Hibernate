package ua.module6.projectsystem.models;

public class Customer extends AbstractModel {

    private Integer id;
    private String name;
    private String country;

    public Customer() {
        id = 0;
        name = "";
        country = "";
    }

    public Customer(String[] params) throws ArrayIndexOutOfBoundsException {
        this.name = params[1].trim();
        this.country = params[2].trim();
    }

    public Customer(Customer currentDbModel, String[] params) throws ArrayIndexOutOfBoundsException {
        this.name = params[2].isBlank() ? currentDbModel.getName() : params[2].trim();
        this.country = params[3].isBlank() ? currentDbModel.getCountry() : params[3].trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

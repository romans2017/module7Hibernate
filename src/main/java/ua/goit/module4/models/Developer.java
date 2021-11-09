package ua.goit.module4.models;

public class Developer extends AbstractModel {

    private Integer id;
    private Integer age;
    private Integer company_id;
    private String company_name;
    private String name;
    private Integer salary;

    public Developer() {
    }

    public Developer(String[] params) throws ArrayIndexOutOfBoundsException, NumberFormatException {
        this.name = params[1].trim();
        this.age = Integer.parseInt(params[2].trim());
        this.company_id = Integer.parseInt(params[3].trim());
        this.salary = Integer.parseInt(params[4].trim());
    }

    public Developer(Developer currentDbModel, String[] params) throws ArrayIndexOutOfBoundsException, NumberFormatException {
        this.name = params[2].isBlank() ? currentDbModel.getName() : params[2].trim();
        this.age = params[3].isBlank() ? currentDbModel.getAge() : Integer.parseInt(params[3].trim());
        this.company_id = params[4].isBlank() ? currentDbModel.getCompany_id() : Integer.parseInt(params[4].trim());
        this.salary = params[5].isBlank() ? currentDbModel.getSalary() : Integer.parseInt(params[5].trim());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", age=" + age +
                ", company_id=" + company_id +
                ", company_name='" + company_name + '\'' +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}

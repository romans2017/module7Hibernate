package ua.goit.module4.models;

public class Developer extends AbstractModel {

    private Integer id;
    private Integer age;
    private Integer company_id;
    private String name;
    private Integer salary;

    public Developer() {
    }

    public Developer(Integer age, Integer company_id, String name, Integer salary) {
        this.age = age;
        this.company_id = company_id;
        this.name = name;
        this.salary = salary;
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

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", age=" + age +
                ", company_id=" + company_id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}

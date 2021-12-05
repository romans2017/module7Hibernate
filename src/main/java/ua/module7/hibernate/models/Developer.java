package ua.module7.hibernate.models;

public class Developer extends AbstractModel {

    private Integer id;
    private Integer age;
    private Integer company_id;
    private String company_name;
    private String name;
    private Integer salary;

    public Developer() {
        id = 0;
        name = "";
        age = 0;
        company_id = -1;
        company_name = "";
        salary = 0;
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

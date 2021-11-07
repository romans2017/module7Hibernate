package ua.goit.module4.models;

import java.util.Date;

public class Project extends AbstractModel {

    private Integer id;
    private Integer company_id;
    private Integer cost;
    private Integer customer_id;
    private String description;
    private String name;
    private Date creation_date;
    private String company_name;
    private String customer_name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", company_id=" + company_id +
                ", cost=" + cost +
                ", customer_id=" + customer_id +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", creation_date=" + creation_date +
                ", company_name='" + company_name + '\'' +
                ", customer_name='" + customer_name + '\'' +
                '}';
    }
}

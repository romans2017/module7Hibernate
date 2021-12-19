package ua.module7.hibernate.pojo;

public interface Pojo{
    <E extends Pojo> E initEmpty();
    Integer getId();
}

package ua.module7.hibernate.pojo;

public interface Pojo extends Comparable<Pojo> {
    <E extends Pojo> E initEmpty();
    Integer getId();
}

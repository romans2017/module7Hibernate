package ua.module7.hibernate.dao;

import ua.module7.hibernate.pojo.Customer;

import javax.persistence.EntityTransaction;

public class CustomerDao extends AbstractDao<Customer> {

    private static CustomerDao instance;

    private CustomerDao() {}

    public static CustomerDao getInstance() {
        if (instance == null) {
            instance = new CustomerDao();
        }
        return instance;
    }

    @Override
    public void delete(Customer entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            Customer mergedEntity = entityManager.merge(entity);
            mergedEntity.getProjects().forEach(item -> item.setCustomer(null));
            entityManager.remove(mergedEntity);
        } catch (Throwable e) {
            transaction.rollback();
            e.printStackTrace();
        }
        if (transaction.isActive()) {
            transaction.commit();
        }
    }
}

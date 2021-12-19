package ua.module7.hibernate.dao;

import ua.module7.hibernate.pojo.Customer;

import javax.persistence.EntityTransaction;

public class CustomerDao extends AbstractDao<Customer> {

    private static CustomerDao instance;

    private CustomerDao() {
    }

    public static CustomerDao getInstance() {
        if (instance == null) {
            instance = new CustomerDao();
        }
        return instance;
    }

    @Override
    public void delete(Customer entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        ProjectDao dao = ProjectDao.getInstance();
        transaction.begin();
        try {
            Customer customer = read(entity.getId());
            if (customer != null) {
                customer.getProjects().forEach(project -> {
                    project.setCustomer(null);
                    dao.update(project);
                });
                entityManager.remove(customer);
            }
        } catch (Throwable e) {
            transaction.rollback();
            e.printStackTrace();
        }
        if (transaction.isActive()) {
            transaction.commit();
        }
    }
}

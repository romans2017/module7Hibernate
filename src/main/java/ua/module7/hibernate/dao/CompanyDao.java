package ua.module7.hibernate.dao;

import ua.module7.hibernate.pojo.Company;

import javax.persistence.EntityTransaction;

public class CompanyDao extends AbstractDao<Company> {

    private static CompanyDao instance;

    private CompanyDao() {
    }

    public static CompanyDao getInstance() {
        if (instance == null) {
            instance = new CompanyDao();
        }
        return instance;
    }

    @Override
    public void delete(Company entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            Company mergedEntity = entityManager.merge(entity);
            mergedEntity.getProjects().forEach(item -> item.setCompany(null));
            mergedEntity.getDevelopers().forEach(item -> item.setCompany(null));
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

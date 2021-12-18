package ua.module7.hibernate.dao;

import ua.module7.hibernate.pojo.Project;

import javax.persistence.EntityTransaction;

public class ProjectDao extends AbstractDao<Project> {

    private static ProjectDao instance;

    private ProjectDao() {
    }

    public static ProjectDao getInstance() {
        if (instance == null) {
            instance = new ProjectDao();
        }
        return instance;
    }

    @Override
    public void delete(Project entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            Project mergedEntity = entityManager.merge(entity);
            mergedEntity.getDevelopers().forEach(developer -> developer.getProjects().remove(mergedEntity));
            if (mergedEntity.getCompany() != null) {
                mergedEntity.getCompany().getProjects().remove(mergedEntity);
            }
            if (mergedEntity.getCustomer() != null) {
                mergedEntity.getCustomer().getProjects().remove(mergedEntity);
            }
            entityManager.remove(entity);
        } catch (Throwable e) {
            transaction.rollback();
            e.printStackTrace();
        }
        if (transaction.isActive()) {
            transaction.commit();
        }
    }
}

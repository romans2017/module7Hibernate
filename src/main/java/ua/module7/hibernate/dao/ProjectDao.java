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
            Project project = read(entity.getId());
            if (project != null) {
                project.getDevelopers().forEach(developer -> {
                    developer.getProjects().remove(project);
                    entityManager.merge(developer);
                });
                if (project.getCompany() != null) {
                    project.getCompany().removeProject(project);
                }
                if (project.getCustomer() != null) {
                    project.getCustomer().removeProject(project);
                }
                entityManager.remove(entity);
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

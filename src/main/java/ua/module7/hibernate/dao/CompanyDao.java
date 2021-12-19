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
        ProjectDao projectDao = ProjectDao.getInstance();
        DeveloperDao developerDao = DeveloperDao.getInstance();
        transaction.begin();
        try {
            Company company = read(entity.getId());
            if (company != null) {
                company.getProjects().forEach(project -> {
                    project.setCompany(null);
                    projectDao.update(project);
                });
                company.getDevelopers().forEach(developer -> {
                    developer.setCompany(null);
                    developerDao.update(developer);
                });
                entityManager.remove(company);
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

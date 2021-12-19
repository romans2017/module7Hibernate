package ua.module7.hibernate.dao;

import org.hibernate.query.criteria.internal.path.SetAttributeJoin;
import ua.module7.hibernate.pojo.Developer;
import ua.module7.hibernate.pojo.Project;
import ua.module7.hibernate.pojo.Skill;

import javax.persistence.EntityTransaction;
import javax.persistence.criteria.*;
import java.util.List;

public class DeveloperDao extends AbstractDao<Developer> {

    private static DeveloperDao instance;

    private DeveloperDao() {
    }

    public static DeveloperDao getInstance() {
        if (instance == null) {
            instance = new DeveloperDao();
        }
        return instance;
    }

    public List<Developer> readJavaDevelopers() {
        List<String> javaList = List.of("java", "Java", "JAVA");
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Developer> criteriaQuery = criteriaBuilder.createQuery(Developer.class);
        Root<Developer> root = criteriaQuery.from(Developer.class);
        Fetch<Developer, Skill> fetch = root.fetch("skills", JoinType.INNER);
        criteriaQuery.select(root);
        criteriaQuery
                .where(((SetAttributeJoin<Developer, Skill>) fetch).get("language").in(javaList))
                .distinct(true)
                .orderBy(criteriaBuilder.asc(root.get("id")));

        return entityManager
                .createQuery(criteriaQuery)
                .getResultList();
    }

    public List<Developer> readMiddleDevelopers() {
        List<String> javaList = List.of("middle", "Middle", "MIDDLE");
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Developer> criteriaQuery = criteriaBuilder.createQuery(Developer.class);
        Root<Developer> root = criteriaQuery.from(Developer.class);
        Fetch<Developer, Skill> fetch = root.fetch("skills", JoinType.INNER);
        criteriaQuery.select(root);
        criteriaQuery
                .where(((SetAttributeJoin<Developer, Skill>) fetch).get("level").in(javaList))
                .distinct(true)
                .orderBy(criteriaBuilder.asc(root.get("id")));

        return entityManager
                .createQuery(criteriaQuery)
                .getResultList();
    }

    public List<Developer> readDevelopersByProject(Integer projectId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Developer> criteriaQuery = criteriaBuilder.createQuery(Developer.class);
        Root<Developer> root = criteriaQuery.from(Developer.class);
        Fetch<Developer, Project> fetch = root.fetch("projects", JoinType.INNER);
        criteriaQuery.select(root);
        criteriaQuery
                .where(criteriaBuilder.equal(((SetAttributeJoin<Developer, Project>) fetch).get("id"), projectId))
                .distinct(true)
                .orderBy(criteriaBuilder.asc(root.get("id")));

        return entityManager
                .createQuery(criteriaQuery)
                .getResultList();
    }

    @Override
    public void delete(Developer entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            Developer developer = read(entity.getId());
            if (developer != null) {
                if (developer.getCompany() != null) {
                    developer.getCompany().removeDeveloper(developer);
                }
                developer.getProjects().forEach(project -> {
                    Project readProject = ProjectDao.getInstance().read(project.getId());
                    readProject.getDevelopers().remove(developer);
                });
                developer.getSkills().forEach(skill -> {
                    Skill readSkill = SkillDao.getInstance().read(skill.getId());
                    readSkill.getDevelopers().remove(developer);
                });
                entityManager.remove(developer);
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

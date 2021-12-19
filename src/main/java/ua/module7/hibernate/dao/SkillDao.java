package ua.module7.hibernate.dao;

import ua.module7.hibernate.pojo.Skill;

import javax.persistence.EntityTransaction;

public class SkillDao extends AbstractDao<Skill> {

    private static SkillDao instance;

    private SkillDao() {
    }

    public static SkillDao getInstance() {
        if (instance == null) {
            instance = new SkillDao();
        }
        return instance;
    }

    @Override
    public void delete(Skill entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        DeveloperDao developerDao = DeveloperDao.getInstance();
        transaction.begin();
        try {
            Skill skill = read(entity.getId());
            if (skill != null) {
                skill.getDevelopers().forEach(developer -> {
                    developer.getSkills().remove(skill);
                    developerDao.update(developer);
                });
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
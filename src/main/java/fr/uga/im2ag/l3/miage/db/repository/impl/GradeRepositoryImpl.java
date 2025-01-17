package fr.uga.im2ag.l3.miage.db.repository.impl;

import fr.uga.im2ag.l3.miage.db.repository.api.GradeRepository;
import fr.uga.im2ag.l3.miage.db.model.Grade;
import fr.uga.im2ag.l3.miage.db.model.Subject;

import javax.persistence.EntityManager;
import javax.persistence.*;
import java.util.List;

public class GradeRepositoryImpl extends BaseRepositoryImpl implements GradeRepository {

    /**
     * Build a base repository
     *
     * @param entityManager the entity manager
     */
    public GradeRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Grade> findHighestGrades(int limit) {
        // TODO
        String jql = "select g from Grade order by value desc";
        List<Grade> retour = entityManager.createQuery(jql, Grade.class)
                            .getResultList();
        return retour;
    }

    @Override
    public List<Grade> findHighestGradesBySubject(int limit, Subject subject) {
        String jql = "select g from Grade where g.subject = :subject order by g.value desc";
        List<Grade> retour = entityManager.createQuery(jql, Grade.class)
        .setParameter("subject", subject)
        .setMaxResults(limit)
        .getResultList();
        return retour;
    }

    @Override
    public void save(Grade entity) {
       entityManager.persist(entity);
    }

    @Override
    public void delete(Grade entity) {
        entityManager.remove(entity);
    }

    @Override
    public Grade findById(Long id) {
        return null;
    }

    @Override
    public List<Grade> getAll() {
        // TODO
        List<Grade> retour = entityManager.createNamedQuery("getallgrade", Grade.class)
                                            .getResultList();
        return retour;
    }
}

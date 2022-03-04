package fr.uga.im2ag.l3.miage.db.repository.impl;

import fr.uga.im2ag.l3.miage.db.repository.api.TeacherRepository;
import fr.uga.im2ag.l3.miage.db.model.Teacher;

import javax.persistence.EntityManager;
import java.util.List;
import javax.persistence.*;

public class TeacherRepositoryImpl extends BaseRepositoryImpl implements TeacherRepository {

    /**
     * Build a base repository
     *
     * @param entityManager the entity manager
     */
    public TeacherRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }


    @Override
    public Teacher findHeadingGraduationClassByYearAndName(Integer year, String name) {
        // TODO
        //pas compris
        return null;
    }

    @Override
    public void save(Teacher entity) {
        entityManager.persist(entity);

    }

    @Override
    public void delete(Teacher entity) {
        entityManager.remove(entity);

    }

    @Override
    public Teacher findById(Long id) {
        return entityManager.find(Teacher.class, id);
    }

    @Override
    public List<Teacher> getAll() {
        List<Teacher> retour = (List<Teacher>) entityManager.createNamedQuery("getall", Teacher.class)
                                                .getResultList();
        return retour;
    }
}

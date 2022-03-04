package fr.uga.im2ag.l3.miage.db.repository.impl;

import fr.uga.im2ag.l3.miage.db.repository.api.SubjectRepository;
import fr.uga.im2ag.l3.miage.db.model.Subject;
import fr.uga.im2ag.l3.miage.db.model.Teacher;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;

public class SubjectRepositoryImpl extends BaseRepositoryImpl implements SubjectRepository {


    public SubjectRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void save(Subject entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(Subject entity) {
        entityManager.remove(entity);
    }

    @Override
    public Subject findById(Long id) {
        // TODO
        /*Subject retour =  entityManager.createNamedQuery("subject_by_id", Subject.class)
            .setParameter("id", id)
            .getSingleResult();
            return retour;*/
        return entityManager.find(Subject.class, id);
    }

    @Override
    public List<Subject> getAll() {
        // TODO
        String requete = "select s from Subject s";
        List<Subject> retour = (List<Subject>) entityManager.createQuery(requete, Subject.class)
                                                .getResultList();
        return retour;
    }

    @Override
    public Collection<Teacher> findTeachers(Long id) {
        
        Collection<Teacher> result = entityManager.createNamedQuery("findTeachers")
                                        .setParameter("id", id)
                                        .getResultList();

        return result;
    }
}

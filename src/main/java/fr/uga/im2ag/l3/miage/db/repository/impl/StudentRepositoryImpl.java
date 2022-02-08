
package fr.uga.im2ag.l3.miage.db.repository.impl;

import fr.uga.im2ag.l3.miage.db.repository.api.StudentRepository;
import fr.uga.im2ag.l3.miage.db.model.Student;

import javax.persistence.EntityManager;
import java.util.List;

public class StudentRepositoryImpl extends BaseRepositoryImpl implements StudentRepository {


    /**
     * Build a base repository
     *
     * @param entityManager the entity manager
     */
    public StudentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void save(Student entity) {
        entityManager.persist(entity);

    }

    @Override
    public void delete(Student entity) {
        entityManager.remove(entity);

    }

    @Override
    public Student findById(Long id) {
        String jql = "select s from student s where s.id = :id";
        Student retour = entityManager.createQuery(jql, Student.class)
                            .setParameter("id", id)
                            .getSingleResult();

        return retour;
    }

    @Override
    public List<Student> getAll() {
        String jql = "select s from Student s";
        List<Student> retour = (List<Student>) entityManager.createQuery(jql, Student.class)
                                                .getResultList();
        return retour;
    }

    @Override
    public List<Student> findStudentHavingGradeAverageAbove(float minAverage) {
        // TODO
        return null;
    }
}

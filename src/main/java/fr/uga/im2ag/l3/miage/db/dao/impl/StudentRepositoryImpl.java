package fr.uga.im2ag.l3.miage.db.dao.impl;

import fr.uga.im2ag.l3.miage.db.dao.api.StudentRepository;
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

    }

    @Override
    public void delete(Student entity) {

    }

    @Override
    public Student findById(Class<Student> entityClass, Long id) {
        return null;
    }

    @Override
    public List<Student> getAll(Class<Student> entityClass) {
        return null;
    }

    @Override
    public List<Student> findStudentHavingGradeAverageAbove(float minAverage) {
        return null;
    }
}
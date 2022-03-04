package fr.uga.im2ag.l3.miage.db.repository;

import fr.uga.im2ag.l3.miage.db.model.Grade;
import fr.uga.im2ag.l3.miage.db.model.Student;
import fr.uga.im2ag.l3.miage.db.repository.api.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

class StudentTest extends Base {

    StudentRepository studentRepository;

    @BeforeEach
    void before() {
        studentRepository = daoFactory.newStudentRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

    @Test
    void shouldSaveStudent() {
        final var student = Fixtures.createStudent(Fixtures.createClass());

        entityManager.getTransaction().begin();
        studentRepository.save(student);
        entityManager.getTransaction().commit();
        entityManager.detach(student);

        var pStudent = studentRepository.findById(student.getId());
        assertThat(pStudent).isNotNull().isNotSameAs(student);
        assertThat(pStudent.getId()).isEqualTo(student.getId());     
    }

    @Test
    void shouldFindStudentHavingGradeAverageAbove() {
        List<Student> list = new ArrayList<Student>();

        List<Grade> notes = new ArrayList<Grade>();

        notes.add(Fixtures.createGrade(Fixtures.createSubject()).setValue( (float) 10).setWeight( (float) 1));
        notes.add(Fixtures.createGrade(Fixtures.createSubject()).setValue( (float) 15).setWeight( (float) 1));
        notes.add(Fixtures.createGrade(Fixtures.createSubject()).setValue( (float) 15).setWeight( (float) 1));

        final var st1 = Fixtures.createStudent(Fixtures.createClass()).setGrades(notes);

        notes = new ArrayList<>();
        notes.add(Fixtures.createGrade(Fixtures.createSubject()).setValue( (float) 10).setWeight( (float) 1));
        notes.add(Fixtures.createGrade(Fixtures.createSubject()).setValue( (float) 15).setWeight( (float) 1));
        notes.add(Fixtures.createGrade(Fixtures.createSubject()).setValue( (float) 15).setWeight( (float) 1));

        final var st2 = Fixtures.createStudent(Fixtures.createClass()).setGrades(notes);

        notes = new ArrayList<>();
        notes.add(Fixtures.createGrade(Fixtures.createSubject()).setValue( (float) 10).setWeight( (float) 1));
        notes.add(Fixtures.createGrade(Fixtures.createSubject()).setValue( (float) 15).setWeight( (float) 1));
        notes.add(Fixtures.createGrade(Fixtures.createSubject()).setValue( (float) 5).setWeight( (float) 1));

        final var st3 = Fixtures.createStudent(Fixtures.createClass()).setGrades(notes);

        entityManager.getTransaction().begin();
        studentRepository.save(st1);
        studentRepository.save(st2);
        studentRepository.save(st3);

        entityManager.getTransaction().commit();
        entityManager.detach(st1);
        entityManager.detach(st2);
        entityManager.detach(st3);

        var pSt1 = studentRepository.findById(st1.getId());
        assertThat(pSt1).isNotNull().isNotSameAs(st1);

        var pSt2 = studentRepository.findById(st2.getId());
        assertThat(pSt2).isNotNull().isNotSameAs(st2);

        var pSt3 = studentRepository.findById(st3.getId());
        assertThat(pSt3).isNotNull().isNotSameAs(st3);

        list.add(pSt1);
        list.add(pSt2);

        assertThat(studentRepository.findStudentHavingGradeAverageAbove(12)).isEqualTo(list);
    }

}

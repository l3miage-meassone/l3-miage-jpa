package fr.uga.im2ag.l3.miage.db.repository;

import fr.uga.im2ag.l3.miage.db.model.Student;
import fr.uga.im2ag.l3.miage.db.model.Teacher;
import fr.uga.im2ag.l3.miage.db.repository.api.TeacherRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TeacherTest extends Base {

    TeacherRepository teacherRepository;

    @BeforeEach
    void before() {
        teacherRepository = daoFactory.newTeacherRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

    @Test
    void shouldSaveTeacher()  {


        final var teacher = Fixtures.createTeacher(Fixtures.createSubject(), Fixtures.createClass(), Fixtures.createStudent(Fixtures.createClass()));

        entityManager.getTransaction().begin();
        teacherRepository.save(teacher);
        entityManager.getTransaction().commit();
        entityManager.detach(teacher);

        var pTeacher = teacherRepository.findById(teacher.getId());
        assertThat(pTeacher).isNotNull().isNotSameAs(teacher);
        assertThat(pTeacher.getId()).isEqualTo(teacher.getId());         
    }

    @Test
    void shouldFindHeadingGraduationClassByYearAndName() {
        // TODO
    }

}

package fr.uga.im2ag.l3.miage.db.repository;

import fr.uga.im2ag.l3.miage.db.repository.api.SubjectRepository;
import fr.uga.im2ag.l3.miage.db.repository.api.TeacherRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SubjectTest extends Base {

    SubjectRepository subjectRepository;

    @BeforeEach
    void before() {
        subjectRepository = daoFactory.newSubjectRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

    @Test
    void shouldSaveSubject() {

        final var subject = Fixtures.createSubject();

        entityManager.getTransaction().begin();
        subjectRepository.save(subject);
        entityManager.getTransaction().commit();
        entityManager.detach(subject);

        var pSubject = subjectRepository.findById(subject.getId());
        assertThat(pSubject).isNotNull().isNotSameAs(subject);
        assertThat(pSubject.getName()).isEqualTo(subject.getName());

    }

    @Test
    void shouldFindTeachersForSubject() {
        final var subject1 = Fixtures.createSubject();
        final var prof1 = Fixtures.createTeacher(subject1, Fixtures.createClass(), Fixtures.createStudent(Fixtures.createClass()));
        final var prof2 = Fixtures.createTeacher(subject1, Fixtures.createClass(), Fixtures.createStudent(Fixtures.createClass()));
        final var prof3 = Fixtures.createTeacher(Fixtures.createSubject(), Fixtures.createClass(), Fixtures.createStudent(Fixtures.createClass()));

        TeacherRepository profRepository = daoFactory.newTeacherRepository(entityManager);

        entityManager.getTransaction().begin();
        subjectRepository.save(subject1);
        profRepository.save(prof1);
        profRepository.save(prof2);
        profRepository.save(prof3);
        entityManager.getTransaction().commit();
        entityManager.detach(subject1);
        entityManager.detach(prof1);
        entityManager.detach(prof2);
        entityManager.detach(prof3);

        var pSubject = subjectRepository.findById(subject1.getId());
        var pProf1 = profRepository.findById(prof1.getId());
        var pProf2 = profRepository.findById(prof2.getId());
        var pProf3 = profRepository.findById(prof3.getId());

        assertThat(pSubject).isNotNull().isNotSameAs(subject1);
        assertThat(pSubject.getName()).isEqualTo(subject1.getName());

        assertThat(pProf1).isNotNull().isNotSameAs(prof1);
        assertThat(pProf1.getLastName()).isEqualTo(prof1.getLastName());

        assertThat(pProf2).isNotNull().isNotSameAs(prof2);
        assertThat(pProf2.getLastName()).isEqualTo(prof2.getLastName());

        assertThat(pProf3).isNotNull().isNotSameAs(prof3);
        assertThat(pProf3.getLastName()).isEqualTo(prof3.getLastName());

        assertThat(pProf1.getTeaching()).isEqualTo(pSubject);
        assertThat(pProf2.getTeaching()).isEqualTo(pSubject);
        assertThat(pProf3.getTeaching()).isNotEqualTo(pSubject);

    }

}

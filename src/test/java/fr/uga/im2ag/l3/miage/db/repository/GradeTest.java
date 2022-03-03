package fr.uga.im2ag.l3.miage.db.repository;

import fr.uga.im2ag.l3.miage.db.model.Grade;
import fr.uga.im2ag.l3.miage.db.repository.api.GradeRepository;
import fr.uga.im2ag.l3.miage.db.repository.api.SubjectRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

class GradeTest extends Base {

    GradeRepository gradeRepository;

    @BeforeEach
    void before() {
        gradeRepository = daoFactory.newGradeRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

    @Test
    void shouldSaveGrade() {
        final var grade = Fixtures.createGrade(Fixtures.createSubject());

        entityManager.getTransaction().begin();
        gradeRepository.save(grade);
        entityManager.getTransaction().commit();
        entityManager.detach(grade);

        var pGrade = gradeRepository.findById(grade.getId());
        assertThat(pGrade).isNotNull().isNotSameAs(grade);
        assertThat(pGrade.getId()).isEqualTo(grade.getId());    
    }

    @Test
    void shouldFailUpgradeGrade() {
        // TODO, ici tester que la mise à jour n'a pas eu lieu.
        //pas compris ce que je dois faire
    }

    @Test
    void shouldFindHighestGrades() {
        // TODO
        final var grade1 = Fixtures.createGrade(Fixtures.createSubject()).setValue((float)20);
        final var grade2 = Fixtures.createGrade(Fixtures.createSubject()).setValue((float)3);
        final var grade3 = Fixtures.createGrade(Fixtures.createSubject()).setValue((float)12);
        final var grade4 = Fixtures.createGrade(Fixtures.createSubject()).setValue((float)8);

        entityManager.getTransaction().begin();
        gradeRepository.save(grade1);
        gradeRepository.save(grade2);
        gradeRepository.save(grade3);
        gradeRepository.save(grade4);

        entityManager.getTransaction().commit();
        entityManager.detach(grade1);
        entityManager.detach(grade2);
        entityManager.detach(grade2);
        entityManager.detach(grade2);

        var pGrade1 = gradeRepository.findById(grade1.getId());
        assertThat(pGrade1).isNotNull().isNotSameAs(grade1);

        var pGrade2 = gradeRepository.findById(grade2.getId());
        assertThat(pGrade1).isNotNull().isNotSameAs(grade2);

        var pGrade3 = gradeRepository.findById(grade3.getId());
        assertThat(pGrade1).isNotNull().isNotSameAs(grade3);

        var pGrade4 = gradeRepository.findById(grade4.getId());
        assertThat(pGrade1).isNotNull().isNotSameAs(grade4);

        List<Grade> list = new ArrayList<Grade>();
        list.add(pGrade1);
        list.add(pGrade3);
        assertThat(gradeRepository.findHighestGrades(2)).isEqualTo(list);
    }

    @Test
    void shouldFindHighestGradesBySubject() {
        // TODO

        SubjectRepository subjectRepository = daoFactory.newSubjectRepository(entityManager);
        
        final var subject1 = Fixtures.createSubject().setName("maths");
        final var subject2 = Fixtures.createSubject().setName("geo");
        
        final var grade1 = Fixtures.createGrade(subject2).setValue((float)20);
        final var grade2 = Fixtures.createGrade(subject1).setValue((float)3);
        final var grade3 = Fixtures.createGrade(subject1).setValue((float)12);
        final var grade4 = Fixtures.createGrade(subject1).setValue((float)8);

        entityManager.getTransaction().begin();
        gradeRepository.save(grade1);
        gradeRepository.save(grade2);
        gradeRepository.save(grade3);
        gradeRepository.save(grade4);
        subjectRepository.save(subject1);
        subjectRepository.save(subject2);

        entityManager.getTransaction().commit();
        entityManager.detach(grade1);
        entityManager.detach(grade2);
        entityManager.detach(grade2);
        entityManager.detach(grade2);
        entityManager.detach(subject1);
        entityManager.detach(subject2);

        var pGrade1 = gradeRepository.findById(grade1.getId());
        assertThat(pGrade1).isNotNull().isNotSameAs(grade1);

        var pGrade2 = gradeRepository.findById(grade2.getId());
        assertThat(pGrade1).isNotNull().isNotSameAs(grade2);

        var pGrade3 = gradeRepository.findById(grade3.getId());
        assertThat(pGrade1).isNotNull().isNotSameAs(grade3);

        var pGrade4 = gradeRepository.findById(grade4.getId());
        assertThat(pGrade1).isNotNull().isNotSameAs(grade4);

        var pSubject1 = subjectRepository.findById(subject1.getId());
        assertThat(pSubject1).isNotNull().isNotSameAs(subject1);
        assertThat(pSubject1.getName()).isEqualTo(subject1.getName());

        var pSubject2 = subjectRepository.findById(subject2.getId());
        assertThat(pSubject2).isNotNull().isNotSameAs(subject2);
        assertThat(pSubject2.getName()).isEqualTo(subject2.getName());

        List<Grade> list = new ArrayList<Grade>();
        list.add(pGrade3);
        list.add(pGrade4);
        assertThat(gradeRepository.findHighestGradesBySubject(2, pSubject1)).isEqualTo(list);


    }

}

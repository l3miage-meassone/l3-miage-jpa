package fr.uga.im2ag.l3.miage.db.repository;

import fr.uga.im2ag.l3.miage.db.repository.api.GraduationClassRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GraduationClassTest extends Base {

    GraduationClassRepository classRepository;

    @BeforeEach
    void before() {
        classRepository = daoFactory.newGraduationClassRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

    @Test
    void shouldSaveClass() {
        final var classe = Fixtures.createClass();

        entityManager.getTransaction().begin();
        classRepository.save(classe);
        entityManager.getTransaction().commit();
        entityManager.detach(classe);

        var pClasse = classRepository.findById(classe.getId());
        assertThat(pClasse).isNotNull().isNotSameAs(classe);
        assertThat(pClasse.getName()).isEqualTo(classe.getName());    }


    @Test
    void shouldFindByYearAndName() {
        // TODO

        final var classe1 = Fixtures.createClass().setName("nom1").setYear(2012);
        final var classe2 = Fixtures.createClass().setName("nom2").setYear(2013);
        final var classe3 = Fixtures.createClass().setName("nom1").setYear(2013);
        final var classe4 = Fixtures.createClass().setName("nom3").setYear(2012);

        entityManager.getTransaction().begin();
        classRepository.save(classe1);
        classRepository.save(classe2);
        classRepository.save(classe3);
        classRepository.save(classe4);

        entityManager.getTransaction().commit();
        entityManager.detach(classe1);
        entityManager.detach(classe2);
        entityManager.detach(classe3);
        entityManager.detach(classe4);

        var pClasse1 = classRepository.findById(classe1.getId());
        assertThat(pClasse1).isNotNull().isNotSameAs(classe1);
        assertThat(pClasse1.getName()).isEqualTo(classe1.getName());    
    
        var pClasse2 = classRepository.findById(classe2.getId());
        assertThat(pClasse2).isNotNull().isNotSameAs(classe2);
        assertThat(pClasse2.getName()).isEqualTo(classe2.getName());    

        var pClasse3 = classRepository.findById(classe3.getId());
        assertThat(pClasse3).isNotNull().isNotSameAs(classe3);
        assertThat(pClasse3.getName()).isEqualTo(classe3.getName());    

        var pClasse4 = classRepository.findById(classe4.getId());
        assertThat(pClasse4).isNotNull().isNotSameAs(classe4);
        assertThat(pClasse4.getName()).isEqualTo(classe4.getName());    

        assertThat(classRepository.findByYearAndName(2012, "nom1")).isEqualTo(pClasse1);
    }


}

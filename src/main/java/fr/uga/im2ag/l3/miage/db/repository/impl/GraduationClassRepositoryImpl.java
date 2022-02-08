package fr.uga.im2ag.l3.miage.db.repository.impl;

import fr.uga.im2ag.l3.miage.db.repository.api.GraduationClassRepository;
import fr.uga.im2ag.l3.miage.db.model.GraduationClass;

import javax.persistence.EntityManager;
import java.util.List;

public class GraduationClassRepositoryImpl extends BaseRepositoryImpl implements GraduationClassRepository {

    public GraduationClassRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public GraduationClass findByYearAndName(Integer year, String name) {
        GraduationClass retour = (GraduationClass) entityManager.createNamedQuery("findByYearAndName")
                                                                .setParameter("year", year)
                                                                .setParameter("name", name)
                                                                .getSingleResult();
        return retour;
    }

    @Override
    public void save(GraduationClass entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(GraduationClass entity) {
        entityManager.remove(entity);
    }

    @Override
    public GraduationClass findById(Long id) {
        String requete = "select g from GraduationClass g where g.id = :id";
        GraduationClass retour = (GraduationClass) entityManager.createQuery(requete, GraduationClass.class)
                                                    .setParameter("id", id)
                                                    .getResultList();
        return retour;
    }

    @Override
    public List<GraduationClass> getAll() {
        String requete = "select g from GraduationClass g";
        List<GraduationClass> retour = (List<GraduationClass>) entityManager.createQuery(requete, GraduationClass.class)
                                                .getResultList();
        return retour;
    }
}

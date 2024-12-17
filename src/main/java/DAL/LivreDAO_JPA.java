/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author Evgenii Morgunov
 */

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.spi.PersistenceUnitInfo;
import model.Livre;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;

public class LivreDAO_JPA implements IDAO<Livre> {
    private EntityManager em;

    public LivreDAO_JPA() {
        PersistenceUnitInfo catalogPUInfo =
                new CatalogPersistenceUnit();
        EntityManagerFactory factory =
                new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(catalogPUInfo,
                        new HashMap<>());
        this.em = factory.createEntityManager();
    }

    @Override
    public void create(Livre livre) {
        em.getTransaction().begin();
        em.persist(livre);
        em.getTransaction().commit();
    }

    @Override
    public Livre findById(Long id) {
        return em.find(Livre.class, id);
    }

    @Override
    public List<Livre> findAll() {
        return em
                .createQuery("SELECT l FROM Livre l", Livre.class)
                .getResultList();
    }

    @Override
    public void update(Livre livre) {
        em.getTransaction().begin();
        em.merge(livre);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Livre livre) {
        em.getTransaction().begin();
        em.remove(em.contains(livre) ? livre : em.merge(livre));
        em.getTransaction().commit();
    }
}

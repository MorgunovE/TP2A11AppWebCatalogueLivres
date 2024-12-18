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
import model.Basket;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;

public class BasketDAO_JPA implements IDAO<Basket> {
    private EntityManager em;

    public BasketDAO_JPA() {
        PersistenceUnitInfo catalogPUInfo =
                new CatalogPersistenceUnit();
        EntityManagerFactory factory =
                new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(catalogPUInfo,
                        new HashMap<>());
        this.em = factory.createEntityManager();
    }

    @Override
    public void create(Basket basket) {
        em.getTransaction().begin();
        em.persist(basket);
        em.getTransaction().commit();
    }

    @Override
    public Basket findById(Long id) {
        return em.find(Basket.class, id);
    }

    @Override
    public List<Basket> findAll() {
        return em
                .createQuery("SELECT b FROM Basket b",
                        Basket.class).getResultList();
    }

    @Override
    public void update(Basket basket) {
        em.getTransaction().begin();
        em.merge(basket);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Basket basket) {
        em.getTransaction().begin();
        em.remove(em.contains(basket) ? basket : em.merge(basket));
        em.getTransaction().commit();
    }
}
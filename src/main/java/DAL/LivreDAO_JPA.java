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
import jakarta.persistence.TypedQuery;
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
        try {
            em.getTransaction().begin();
            em.persist(livre);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Livre findById(Long id) {
        return em.find(Livre.class, id);
    }

    @Override
    public List<Livre> findAll() {
        return em
                .createQuery("SELECT l FROM Livre l",
                        Livre.class).getResultList();
    }

    @Override
    public void update(Livre livre) {
        try {
            em.getTransaction().begin();
            em.merge(livre);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Livre livre) {
        try {
            em.getTransaction().begin();
            em.remove(em.contains(livre) ? livre : em.merge(livre));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public List<Livre> findByTitle(String title) {
        TypedQuery<Livre> query = em
                .createQuery("SELECT l FROM Livre l " +
                        "WHERE l.title = :title", Livre.class);
        query.setParameter("title", title);
        return query.getResultList();
    }

    public List<Livre> findByAuthor(String author) {
        TypedQuery<Livre> query = em
                .createQuery("SELECT l FROM Livre l " +
                        "WHERE l.author = :author", Livre.class);
        query.setParameter("author", author);
        return query.getResultList();
    }

    public List<Livre> findByGenre(String genre) {
        TypedQuery<Livre> query = em
                .createQuery("SELECT l FROM Livre l " +
                        "WHERE l.genre = :genre", Livre.class);
        query.setParameter("genre", genre);
        return query.getResultList();
    }

    public List<Livre> findByPriceLessThanOrEqual(double price) {
        TypedQuery<Livre> query = em
                .createQuery("SELECT l FROM Livre l " +
                        "WHERE l.price <= :price", Livre.class);
        query.setParameter("price", price);
        return query.getResultList();
    }
}

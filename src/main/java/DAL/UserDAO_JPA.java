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
import model.User;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;

public class UserDAO_JPA implements IDAO<User> {
    private EntityManager em;

    public UserDAO_JPA() {
        PersistenceUnitInfo catalogPUInfo =
                new CatalogPersistenceUnit();
        EntityManagerFactory factory =
                new HibernatePersistenceProvider()
                        .createContainerEntityManagerFactory(catalogPUInfo,
                                new HashMap<>());
        this.em = factory.createEntityManager();
    }

    @Override
    public void create(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @Override
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        return em
                .createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    @Override
    public void update(User user) {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    @Override
    public void delete(User user) {
        em.getTransaction().begin();
        em.remove(em.contains(user) ? user : em.merge(user));
        em.getTransaction().commit();
    }
}
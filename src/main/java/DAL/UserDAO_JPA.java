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
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
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
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        try {
            em.getTransaction().begin();
            em.remove(em.contains(user) ? user : em.merge(user));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    /**
     * Trouve un utilisateur par son nom et prénom.
     *
     * @param name      le nom de l'utilisateur
     * @param familyName le prénom de l'utilisateur
     * @return l'utilisateur trouvé, ou null en cas d'erreur
     */
    public List<User> findByNameAndFamilyName(String name,
                                              String familyName) {
        TypedQuery<User> query = em
                .createQuery("SELECT u FROM User u " +
                        "WHERE u.name = :name AND " +
                        "u.familyName = :familyName", User.class);
        query.setParameter("name", name);
        query.setParameter("familyName", familyName);
        return query.getResultList();
    }

    /**
     * Trouve un utilisateur par son nom.
     *
     * @param tel le tel de l'utilisateur
     * @return l'utilisateur trouvé, ou null en cas d'erreur
     */
    public List<User> findByTel(String tel) {
        TypedQuery<User> query = em
                .createQuery("SELECT u FROM User u " +
                        "WHERE u.tel = :tel", User.class);
        query.setParameter("tel", tel);
        return query.getResultList();
    }

    /**
     * Trouve un utilisateur par son email.
     *
     * @param email l'email de l'utilisateur
     * @return l'utilisateur trouvé, ou null en cas d'erreur
     */
    public List<User> findByEmail(String email) {
        TypedQuery<User> query = em
                .createQuery("SELECT u FROM User u " +
                        "WHERE u.email = :email", User.class);
        query.setParameter("email", email);
        return query.getResultList();
    }
}
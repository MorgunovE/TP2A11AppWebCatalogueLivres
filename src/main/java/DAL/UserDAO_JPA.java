/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 * UserDAO_JPA class for user data access object with JPA
 * @author Evgenii Morgunov
 */

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.User;
import java.util.List;

/**
 * Classe UserDAO_JPA.
 */
public class UserDAO_JPA implements IDAO<User> {
    /**
     * L'entity manager.
     */
    private EntityManager em;

    /**
     * Constructeur de la classe UserDAO_JPA.
     */
    public UserDAO_JPA() {
        this.em = EM_Provider.getInstance().getEntityManager();
    }

    /**
     * Crée un utilisateur.
     *
     * @param user l'utilisateur à créer
     */
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

    /**
     * Trouve un utilisateur par son identifiant.
     *
     * @param id l'identifiant de l'utilisateur
     * @return l'utilisateur trouvé, ou null en cas d'erreur
     */
    @Override
    public User findById(Long id) {
        return em.find(User.class, id);
    }

   /**
     * Trouve tous les utilisateurs.
     *
     * @return la liste de tous les utilisateurs, ou null en cas d'erreur
     */
    @Override
    public List<User> findAll() {
        TypedQuery<User> query = em
                .createQuery(SQL_BOX.FIND_ALL_USERS_JPQL, User.class);
        return query.getResultList();
    }

    /**
     * Met à jour un utilisateur.
     *
     * @param user l'utilisateur à mettre à jour
     */
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

    /**
     * Supprime un utilisateur.
     *
     * @param user l'utilisateur à supprimer
     */
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
                .createQuery(SQL_BOX.
                        FIND_USER_BY_NAME_AND_FAMILY_NAME_JPQL,
                        User.class);
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
                .createQuery(SQL_BOX.FIND_USER_BY_TEL_JPQL,
                        User.class);
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
                .createQuery(SQL_BOX.FIND_USER_BY_EMAIL_JPQL,
                        User.class);
        query.setParameter("email", email);
        return query.getResultList();
    }
}
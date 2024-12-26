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
import jakarta.persistence.TypedQuery;
import model.Livre;
import java.util.List;

/**
 * Classe LivreDAO_JPA.
 */
public class LivreDAO_JPA implements IDAO<Livre> {

    /**
     * L'entity manager.
     */
    private EntityManager em;

    /**
     * Constructeur de la classe LivreDAO_JPA.
     */
    public LivreDAO_JPA() {
        this.em = EM_Provider.getInstance().getEntityManager();
    }

    /**
     * Crée un livre.
     *
     * @param livre le livre à créer
     */
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

    /**
     * Trouve un livre par son identifiant.
     *
     * @param id l'identifiant du livre
     * @return le livre trouvé, ou null en cas d'erreur
     */
    @Override
    public Livre findById(Long id) {
        return em.find(Livre.class, id);
    }

    /**
     * Trouve tous les livres.
     *
     * @return la liste de tous les livres, ou null en cas d'erreur
     */
    @Override
    public List<Livre> findAll() {
        TypedQuery<Livre> query = em
                .createQuery(SQL_BOX.FIND_ALL_LIVRES_JPQL, Livre.class);
        return query.getResultList();
    }

    /**
     * Met à jour un livre.
     *
     * @param livre le livre à mettre à jour
     */
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

    /**
     * Supprime un livre.
     *
     * @param livre le livre à supprimer
     */
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

    /**
     * Trouve un livre par son titre.
     *
     * @param title le titre du livre
     * @return le livre trouvé, ou null en cas d'erreur
     */
    public List<Livre> findByTitle(String title) {
        TypedQuery<Livre> query = em
                .createQuery(SQL_BOX.FIND_LIVRE_BY_TITLE_JPQL,
                        Livre.class);
        query.setParameter("title", "%" + title + "%");
        return query.getResultList();
    }

    /**
     * Trouve un livre par son auteur.
     *
     * @param author l'auteur du livre
     * @return le livre trouvé, ou null en cas d'erreur
     */
    public List<Livre> findByAuthor(String author) {
        TypedQuery<Livre> query = em
                .createQuery(SQL_BOX.FIND_LIVRE_BY_AUTHOR_JPQL,
                        Livre.class);
        query.setParameter("author", "%" + author + "%");
        return query.getResultList();
    }

    /**
     * Trouve un livre par son genre.
     *
     * @param genre le genre du livre
     * @return le livre trouvé, ou null en cas d'erreur
     */
    public List<Livre> findByGenre(String genre) {
        TypedQuery<Livre> query = em
                .createQuery(SQL_BOX.FIND_LIVRE_BY_GENRE_JPQL,
                        Livre.class);
        query.setParameter("genre", "%" + genre + "%");
        return query.getResultList();
    }

    /**
     * Trouve les livres par prix.
     *
     * @param price le prix du livre
     * @return la liste des livres trouvés, ou null en cas d'erreur
     */
    public List<Livre> findByPriceLessThanOrEqual(double price) {
        TypedQuery<Livre> query = em
                .createQuery(SQL_BOX.
                        FIND_LIVRE_BY_PRICE_LESS_THAN_OR_EQUAL_JPQL,
                        Livre.class);
        query.setParameter("price", price);
        return query.getResultList();
    }
}

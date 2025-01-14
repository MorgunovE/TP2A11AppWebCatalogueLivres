/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 * BasketDAO_JPA class for data access object (DAO) pattern.
 * @author Evgenii Morgunov
 */
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Basket;
import java.util.List;

/**
 * Classe BasketDAO_JPA.
 */
public class BasketDAO_JPA implements IDAO<Basket> {

    /**
     * L'entity manager.
     */
    private EntityManager em;

    /**
     * Constructeur de la classe BasketDAO_JPA.
     */
    public BasketDAO_JPA() {
        this.em = EM_Provider.getInstance().getEntityManager();
    }

    /**
     * Crée un panier.
     *
     * @param basket le panier à créer
     */
    @Override
    public void create(Basket basket) {
        try {
            em.getTransaction().begin();
            em.persist(basket);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    /**
     * Trouve un panier par son identifiant.
     *
     * @param id l'identifiant du panier
     * @return le panier trouvé, ou null en cas d'erreur
     */
    @Override
    public Basket findById(Long id) {
        return em.find(Basket.class, id);
    }

    /**
     * Trouve tous les paniers.
     *
     * @return la liste de tous les paniers, ou null en cas d'erreur
     */
    @Override
    public List<Basket> findAll() {
        TypedQuery<Basket> query = em
                .createQuery(SQL_BOX.FIND_ALL_BASKETS_JPQL,
                        Basket.class);
        return query.getResultList();
    }

    /**
     * Met à jour un panier.
     *
     * @param basket le panier à mettre à jour
     */
    @Override
    public void update(Basket basket) {
        try {
            em.getTransaction().begin();
            em.merge(basket);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    /**
     * Supprime un panier.
     *
     * @param basket le panier à supprimer
     */
    @Override
    public void delete(Basket basket) {
        try {
            em.getTransaction().begin();
            em.remove(em.contains(basket) ? basket : em.merge(basket));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    /**
     * Trouve un panier par l'identifiant de l'utilisateur.
     *
     * @param userId l'identifiant de l'utilisateur
     * @return la liste des paniers trouvés, ou null en cas d'erreur
     */
    public List<Basket> findByUserId(Long userId) {
        TypedQuery<Basket> query = em
                .createQuery(SQL_BOX.FIND_BASKET_BY_USER_ID_JPQL,
                        Basket.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
}
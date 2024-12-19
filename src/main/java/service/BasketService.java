/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author Evgenii Morgunov
 */

import DAL.BasketDAO_JPA;
import DAL.IDAO;
import model.Basket;

import java.util.List;

/**
 * Service pour gérer les opérations liées aux paniers.
 * Cette classe utilise un DAO pour accéder aux données des paniers.
 */
public class BasketService {

    /**
     * Le DAO pour accéder aux données des paniers.
     */
    private IDAO<Basket> basketDAO = new BasketDAO_JPA();

    /**
     * Crée un nouveau panier.
     *
     * @param basket le panier à créer
     */
    public void createBasket(Basket basket) {
        try {
            basketDAO.create(basket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Trouve un panier par son identifiant.
     *
     * @param id l'identifiant du panier
     * @return le panier trouvé, ou null en cas d'erreur
     */
    public Basket findBasketById(Long id) {
        try {
            return basketDAO.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Trouve tous les paniers.
     *
     * @return la liste de tous les paniers, ou null en cas d'erreur
     */
    public List<Basket> findAllBaskets() {
        try {
            return basketDAO.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Met à jour un panier.
     *
     * @param basket le panier à mettre à jour
     */
    public void updateBasket(Basket basket) {
        try {
            basketDAO.update(basket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Supprime un panier.
     *
     * @param basket le panier à supprimer
     */
    public void deleteBasket(Basket basket) {
        try {
            basketDAO.delete(basket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Trouve les paniers par l'identifiant de l'utilisateur.
     *
     * @param userId l'identifiant de l'utilisateur
     * @return la liste des paniers trouvés, ou null en cas d'erreur
     */
    public List<Basket> findBasketsByUserId(Long userId) {
        try {
            return ((BasketDAO_JPA) basketDAO).findByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

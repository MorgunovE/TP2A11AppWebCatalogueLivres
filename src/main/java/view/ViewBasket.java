/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Evgenii Morgunov
 */
import model.Basket;
import service.BasketService;

import java.util.List;

/**
 * Vue pour afficher les informations des paniers.
 * Cette classe utilise un service pour accéder aux données des paniers.
 */
public class ViewBasket {
    private BasketService basketService = new BasketService();

    /**
     * Affiche tous les paniers.
     */
    public void displayAllBaskets() {
        try {
            List<Basket> baskets = basketService.findAllBaskets();
            for (Basket basket : baskets) {
                System.out.println(basket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche un panier par son identifiant.
     *
     * @param id l'identifiant du panier
     */
    public void displayBasketById(Long id) {
        try {
            Basket basket = basketService.findBasketById(id);
            System.out.println(basket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
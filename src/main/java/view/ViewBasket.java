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

public class ViewBasket {
    private BasketService basketService = new BasketService();

    public void displayAllBaskets() {
        List<Basket> baskets = basketService.findAllBaskets();
        for (Basket basket : baskets) {
            System.out.println(basket);
        }
    }

    public void displayBasketById(Long id) {
        Basket basket = basketService.findBasketById(id);
        System.out.println(basket);
    }
}
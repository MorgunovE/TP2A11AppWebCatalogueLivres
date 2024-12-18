/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author 1
 */

import DAL.BasketDAO_JPA;
import DAL.IDAO;
import model.Basket;

import java.util.List;

public class BasketService {
    private IDAO<Basket> basketDAO = new BasketDAO_JPA();

    public void createBasket(Basket basket) {
        basketDAO.create(basket);
    }

    public Basket findBasketById(Long id) {
        return basketDAO.findById(id);
    }

    public List<Basket> findAllBaskets() {
        return basketDAO.findAll();
    }

    public void updateBasket(Basket basket) {
        basketDAO.update(basket);
    }

    public void deleteBasket(Basket basket) {
        basketDAO.delete(basket);
    }
}

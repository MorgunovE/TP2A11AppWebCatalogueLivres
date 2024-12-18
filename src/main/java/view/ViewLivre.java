/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author 1
 */
import model.Livre;
import service.LivreService;

import java.util.List;

public class ViewLivre {
    private LivreService livreService = new LivreService();

    public void displayAllLivres() {
        List<Livre> livres = livreService.findAllLivres();
        for (Livre livre : livres) {
            System.out.println(livre);
        }
    }

    public void displayLivreById(Long id) {
        Livre livre = livreService.findLivreById(id);
        System.out.println(livre);
    }
}

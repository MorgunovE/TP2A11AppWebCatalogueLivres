/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Evgenii Morgunov
 */
import model.Livre;
import service.LivreService;

import java.util.List;

/**
 * Vue pour afficher les informations des livres.
 * Cette classe utilise un service pour accéder aux données des livres.
 */
public class ViewLivre {
    private LivreService livreService = new LivreService();

    /**
     * Affiche tous les livres.
     */
    public void displayAllLivres() {
        try {
            List<Livre> livres = livreService.findAllLivres();
            for (Livre livre : livres) {
                System.out.println(livre);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche un livre par son identifiant.
     *
     * @param id l'identifiant du livre
     */
    public void displayLivreById(Long id) {
        try {
            Livre livre = livreService.findLivreById(id);
            System.out.println(livre);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
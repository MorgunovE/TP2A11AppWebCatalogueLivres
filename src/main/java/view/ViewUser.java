/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Evgenii Morgunov
 */
import model.User;
import service.UserService;

import java.util.List;

/**
 * Vue pour afficher les informations des utilisateurs.
 * Cette classe utilise un service pour accéder aux données des utilisateurs.
 */
public class ViewUser {
    private UserService userService = new UserService();

    /**
     * Affiche tous les utilisateurs.
     */
    public void displayAllUsers() {
        try {
            List<User> users = userService.findAllUsers();
            for (User user : users) {
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche un utilisateur par son identifiant.
     *
     * @param id l'identifiant de l'utilisateur
     */
    public void displayUserById(Long id) {
        try {
            User user = userService.findUserById(id);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
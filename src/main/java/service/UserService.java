/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 * Service pour gérer les opérations liées aux utilisateurs.
 * Cette classe utilise un DAO pour accéder aux données des utilisateurs.
 * @author Evgenii Morgunov
 */

import DAL.IDAO;
import DAL.UserDAO_JPA;
import model.User;

import java.util.List;

/**
 * Service pour gérer les opérations liées aux utilisateurs.
 * Cette classe utilise un DAO pour accéder aux données des utilisateurs.
 */
public class UserService {

    /**
     * Le DAO pour accéder aux données des utilisateurs.
     */
    private IDAO<User> userDAO = new UserDAO_JPA();

    /**
     * Crée un nouvel utilisateur.
     *
     * @param user l'utilisateur à créer
     */
    public void createUser(User user) {
        try {
            userDAO.create(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Trouve un utilisateur par son identifiant.
     *
     * @param id l'identifiant de l'utilisateur
     * @return l'utilisateur trouvé, ou null en cas d'erreur
     */
    public User findUserById(Long id) {
        try {
            return userDAO.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Trouve tous les utilisateurs.
     *
     * @return la liste de tous les utilisateurs, ou null en cas d'erreur
     */
    public List<User> findAllUsers() {
        try {
            return userDAO.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Met à jour un utilisateur.
     *
     * @param user l'utilisateur à mettre à jour
     */
    public void updateUser(User user) {
        try {
            userDAO.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Supprime un utilisateur.
     *
     * @param user l'utilisateur à supprimer
     */
    public void deleteUser(User user) {
        try {
            userDAO.delete(user);
        } catch (Exception e) {
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
    public List<User> findUsersByNameAndFamilyName(String name,
                                                   String familyName) {
        try {
            return ((UserDAO_JPA) userDAO)
                    .findByNameAndFamilyName(name, familyName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Trouve un utilisateur par son nom.
     *
     * @param tel le tel de l'utilisateur
     * @return l'utilisateur trouvé, ou null en cas d'erreur
     */
    public List<User> findUsersByTel(String tel) {
        try {
            return ((UserDAO_JPA) userDAO).findByTel(tel);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Trouve un utilisateur par son email.
     *
     * @param email l'email de l'utilisateur
     * @return l'utilisateur trouvé, ou null en cas d'erreur
     */
    public List<User> findUsersByEmail(String email) {
        try {
            return ((UserDAO_JPA) userDAO).findByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
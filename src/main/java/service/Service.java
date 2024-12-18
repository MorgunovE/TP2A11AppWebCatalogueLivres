package service;

/**
 *
 * @author Evgenii Morgunov
 */

import DAL.LivreDAO_JPA;
import DAL.UserDAO_JPA;
import model.Livre;
import model.User;

import java.util.List;

public class Service {
    private LivreDAO_JPA livreDAO = new LivreDAO_JPA();
    private UserDAO_JPA userDAO = new UserDAO_JPA();

    public void createLivre(Livre livre) {
        livreDAO.create(livre);
    }

    public Livre findLivreById(Long id) {
        return livreDAO.findById(id);
    }

    public List<Livre> findAllLivres() {
        return livreDAO.findAll();
    }

    public void updateLivre(Livre livre) {
        livreDAO.update(livre);
    }

    public void deleteLivre(Livre livre) {
        livreDAO.delete(livre);
    }

    public void createUser(User user) {
        userDAO.create(user);
    }

    public User findUserById(Long id) {
        return userDAO.findById(id);
    }

    public List<User> findAllUsers() {
        return userDAO.findAll();
    }

    public void updateUser(User user) {
        userDAO.update(user);
    }

    public void deleteUser(User user) {
        userDAO.delete(user);
    }
}
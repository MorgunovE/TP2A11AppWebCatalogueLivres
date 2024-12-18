/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author Evgenii Morgunov
 */
import DAL.IDAO;
import DAL.UserDAO_JPA;
import model.User;

import java.util.List;

public class UserService {
    private IDAO<User> userDAO = new UserDAO_JPA();

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

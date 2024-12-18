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

public class ViewUser {
    private UserService userService = new UserService();

    public void displayAllUsers() {
        List<User> users = userService.findAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }

    public void displayUserById(Long id) {
        User user = userService.findUserById(id);
        System.out.println(user);
    }
}

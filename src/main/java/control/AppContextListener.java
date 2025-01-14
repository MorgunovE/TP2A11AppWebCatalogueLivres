/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import service.BasketService;
import service.LivreService;
import service.UserService;

/**
 * Application Lifecycle Listener implementation class AppContextListener
 * @author Evgenii Morgunov
 */
@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        BasketService basketService = new BasketService();
        LivreService livreService = new LivreService();
        UserService userService = new UserService();

        sce.getServletContext().setAttribute("basketService", basketService);
        sce.getServletContext().setAttribute("livreService", livreService);
        sce.getServletContext().setAttribute("userService", userService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cleanup
    }
}

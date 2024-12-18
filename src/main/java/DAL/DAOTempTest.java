package DAL;

import model.Livre;
import model.User;
import model.Basket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DAOTempTest {
    public static void main(String[] args) {
        // Test LivreDAO_JPA test passed
        LivreDAO_JPA livreDAO5 = new LivreDAO_JPA();
        Livre livre5 = new Livre("Test Title", "Test Description", "Test Author", "Test Genre", "test_image.jpg");
        livreDAO5.create(livre5);
        System.out.println("Created Livre: " + livre5);

        Livre foundLivre = livreDAO5.findById(livre5.getId());
        System.out.println("Found Livre: " + foundLivre);

        foundLivre.setTitle("Updated Title");
        livreDAO5.update(foundLivre);
        System.out.println("Updated Livre: " + livreDAO5.findById(foundLivre.getId()));

        livreDAO5.delete(foundLivre);
        System.out.println("Deleted Livre: " + livreDAO5.findById(foundLivre.getId()));

        // Test UserDAO_JPA test passed
        UserDAO_JPA userDAO2 = new UserDAO_JPA();
        User user2 = new User("Test", "User", "123-456-7890", "test.user@example.com");
        userDAO2.create(user2);
        System.out.println("Created User: " + user2);

        User foundUser2 = userDAO2.findById(user2.getId());
        System.out.println("Found User: " + foundUser2);

        foundUser2.setName("Updated Name");
        userDAO2.update(foundUser2);
        System.out.println("Updated User: " + userDAO2.findById(foundUser2.getId()));

        userDAO2.delete(foundUser2);
        System.out.println("Deleted User: " + userDAO2.findById(foundUser2.getId()));

        // Test BasketDAO_JPA passed
        LivreDAO_JPA livreDAO = new LivreDAO_JPA();

        Livre livre1 = new Livre("Test Title 1", "Test Description 1", "Test Author 1", "Test Genre 1", "test_image1.jpg");
        Livre livre2 = new Livre("Test Title 2", "Test Description 2", "Test Author 2", "Test Genre 2", "test_image2.jpg");
        livreDAO.create(livre1);
        livreDAO.create(livre2);

        UserDAO_JPA userDAO = new UserDAO_JPA();

        User user = new User("Test", "User", "123-456-7890", "test.user@example.com");
        userDAO.create(user);

        BasketDAO_JPA basketDAO = new BasketDAO_JPA();

        Basket basket = new Basket(user, Arrays.asList(livre1, livre2));
        basketDAO.create(basket);

        System.out.println("Created Basket: " + basket);

        Basket foundBasket = basketDAO.findById(basket.getId());
        System.out.println("Found Basket: " + foundBasket);

        Livre livre3 = new Livre("Test Title 3", "Test Description 3", "Test Author 3", "Test Genre 3", "test_image3.jpg");
        livreDAO.create(livre3);
        foundBasket.setLivres(new ArrayList<>(Arrays.asList(livre1, livre2, livre3)));
        basketDAO.update(foundBasket);
        System.out.println("Updated Basket: " + basketDAO.findById(foundBasket.getId()));

        Livre livre4 = new Livre("Test Title 4", "Test Description 4", "Test Author 4", "Test Genre 4", "test_image4.jpg");
        livreDAO.create(livre4);
        Basket basket2 = new Basket(user, Arrays.asList(livre4));
        basketDAO.create(basket2);

        System.out.println("Created Baskets: " + basket + ", " + basket2);

        List<Basket> basketsByUser = basketDAO.findByUserId(user.getId());
        System.out.println("Baskets found by user ID: " + basketsByUser);

        basketDAO.delete(foundBasket);
        System.out.println("Deleted Basket: " + basketDAO.findById(foundBasket.getId()));
        basketDAO.delete(basket2);
        System.out.println("Deleted Basket: " + basketDAO.findById(basket2.getId()));

    }
}

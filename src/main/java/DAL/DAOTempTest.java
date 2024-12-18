package DAL;

import model.Livre;
import model.User;
import model.Basket;
import service.BasketService;
import service.LivreService;
import service.UserService;

import java.util.Arrays;
import java.util.List;

public class DAOTempTest {
    public static void main(String[] args) {
        // Initialize services
        LivreService livreService = new LivreService();
        UserService userService = new UserService();
        BasketService basketService = new BasketService();

        // Test LivreService
        Livre livre = new Livre("Test Title", "Test Description", "Test Author", "Test Genre", "test_image.jpg", 10.99, 5);
        livreService.createLivre(livre);
        System.out.println("Created Livre: " + livre);

        Livre foundLivre = livreService.findLivreById(livre.getId());
        System.out.println("Found Livre: " + foundLivre);

        foundLivre.setTitle("Updated Title");
        livreService.updateLivre(foundLivre);
        System.out.println("Updated Livre: " + livreService.findLivreById(foundLivre.getId()));

        List<Livre> livresByTitle = livreService.findLivresByTitle("Updated Title");
        System.out.println("Livres by Title: " + livresByTitle);

        List<Livre> livresByAuthor = livreService.findLivresByAuthor("Test Author");
        System.out.println("Livres by Author: " + livresByAuthor);

        List<Livre> livresByGenre = livreService.findLivresByGenre("Test Genre");
        System.out.println("Livres by Genre: " + livresByGenre);

        List<Livre> livresByPrice = livreService.findLivresByPriceLessThanOrEqual(15.00);
        System.out.println("Livres by Price: " + livresByPrice);

        livreService.deleteLivre(foundLivre);
        System.out.println("Deleted Livre: " + livreService.findLivreById(foundLivre.getId()));

        // Test UserService
        User user = new User("Test", "User", "123-456-7890", "test.user@example.com");
        userService.createUser(user);
        System.out.println("Created User: " + user);

        User foundUser = userService.findUserById(user.getId());
        System.out.println("Found User: " + foundUser);

        foundUser.setName("Updated Name");
        userService.updateUser(foundUser);
        System.out.println("Updated User: " + userService.findUserById(foundUser.getId()));

        List<User> usersByNameAndFamilyName = userService.findUsersByNameAndFamilyName("Updated Name", "User");
        System.out.println("Users by Name and Family Name: " + usersByNameAndFamilyName);

        List<User> usersByTel = userService.findUsersByTel("123-456-7890");
        System.out.println("Users by Tel: " + usersByTel);

        List<User> usersByEmail = userService.findUsersByEmail("test.user@example.com");
        System.out.println("Users by Email: " + usersByEmail);

        userService.deleteUser(foundUser);
        System.out.println("Deleted User: " + userService.findUserById(foundUser.getId()));

        // Test BasketService
        Livre livre1 = new Livre("Test Title 1", "Test Description 1", "Test Author 1", "Test Genre 1", "test_image1.jpg", 10.99, 5);
        Livre livre2 = new Livre("Test Title 2", "Test Description 2", "Test Author 2", "Test Genre 2", "test_image2.jpg", 8.99, 3);
        livreService.createLivre(livre1);
        livreService.createLivre(livre2);

        User user1 = new User("Test", "User1", "123-456-7890", "test.user1@example.com");
        userService.createUser(user1);

        Basket basket = new Basket(user1, Arrays.asList(livre1, livre2));
        basketService.createBasket(basket);
        System.out.println("Created Basket: " + basket);

        Basket foundBasket = basketService.findBasketById(basket.getId());
        System.out.println("Found Basket: " + foundBasket);

        Livre livre3 = new Livre("Test Title 3", "Test Description 3", "Test Author 3", "Test Genre 3", "test_image3.jpg", 12.99, 7);
        livreService.createLivre(livre3);
        foundBasket.setLivres(Arrays.asList(livre1, livre2, livre3));
        basketService.updateBasket(foundBasket);
        System.out.println("Updated Basket: " + basketService.findBasketById(foundBasket.getId()));

        List<Basket> basketsByUser = basketService.findBasketsByUserId(user1.getId());
        System.out.println("Baskets by User ID: " + basketsByUser);

        basketService.deleteBasket(foundBasket);
        System.out.println("Deleted Basket: " + basketService.findBasketById(foundBasket.getId()));
        // Clean up
        livreService.deleteLivre(livre1);
        livreService.deleteLivre(livre2);
        livreService.deleteLivre(livre3);
        userService.deleteUser(user1);

    }
}

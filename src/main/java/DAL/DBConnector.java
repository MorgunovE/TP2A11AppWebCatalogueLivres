/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author Evgenii Morgunov
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe Singleton pour gérer les connexions à la base de données.
 * Cette classe garantit qu'une seule instance de DBConnector est créée.
 */
public class DBConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/CatalogDB";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static DBConnector instance = null;

    /**
     * Constructeur privé pour empêcher l'instanciation.
     */
    private DBConnector() {
        // Constructeur privé pour empêcher l'instanciation
    }

    /**
     * Retourne l'unique instance de DBConnector.
     * Si l'instance n'existe pas, elle est créée.
     *
     * @return l'unique instance de DBConnector
     */
    public static DBConnector getInstance() {
        if (instance == null) {
            synchronized (DBConnector.class) {
                if (instance == null) {
                    instance = new DBConnector();
                }
            }
        }
        return instance;
    }

    /**
     * Retourne une connexion à la base de données.
     *
     * @return un objet Connection à la base de données
     * @throws SQLException si une erreur d'accès à la base de données se produit
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
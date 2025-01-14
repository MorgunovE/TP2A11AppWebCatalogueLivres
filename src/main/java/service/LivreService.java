package service;

/**
 * Service pour gérer les opérations liées aux livres.
 * Cette classe utilise un DAO pour accéder aux données des livres.
 * @autor Evgenii Morgunov
 */

import DAL.IDAO;
import DAL.LivreDAO_JPA;
import model.Livre;

import java.util.List;

/**
 * Service pour gérer les opérations liées aux livres.
 * Cette classe utilise un DAO pour accéder aux données des livres.
 */
public class LivreService {

    /**
     * Le DAO pour accéder aux données des livres.
     */
    private IDAO<Livre> livreDAO = new LivreDAO_JPA();

    /**
     * Crée un nouveau livre.
     *
     * @param livre le livre à créer
     */
    public void createLivre(Livre livre) {
        try {
            livreDAO.create(livre);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Trouve un livre par son identifiant.
     *
     * @param id l'identifiant du livre
     * @return le livre trouvé, ou null en cas d'erreur
     */
    public Livre findLivreById(Long id) {
        try {
            return livreDAO.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Trouve tous les livres.
     *
     * @return la liste de tous les livres, ou null en cas d'erreur
     */
    public List<Livre> findAllLivres() {
        try {
            return livreDAO.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Met à jour un livre.
     *
     * @param livre le livre à mettre à jour
     */
    public void updateLivre(Livre livre) {
        try {
            livreDAO.update(livre);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Supprime un livre.
     *
     * @param livre le livre à supprimer
     */
    public void deleteLivre(Livre livre) {
        try {
            livreDAO.delete(livre);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Trouve les livres par titre.
     *
     * @param title le titre du livre
     * @return la liste des livres trouvés, ou null en cas d'erreur
     */
    public List<Livre> findLivresByTitle(String title) {
        try {
            return ((LivreDAO_JPA) livreDAO).findByTitle(title);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Trouve les livres par auteur.
     *
     * @param author l'auteur du livre
     * @return la liste des livres trouvés, ou null en cas d'erreur
     */
    public List<Livre> findLivresByAuthor(String author) {
        try {
            return ((LivreDAO_JPA) livreDAO).findByAuthor(author);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Trouve les livres par genre.
     *
     * @param genre le genre du livre
     * @return la liste des livres trouvés, ou null en cas d'erreur
     */
    public List<Livre> findLivresByGenre(String genre) {
        try {
            return ((LivreDAO_JPA) livreDAO).findByGenre(genre);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Trouve les livres par prix inférieur ou égal.
     *
     * @param price le prix du livre
     * @return la liste des livres trouvés, ou null en cas d'erreur
     */
    public List<Livre> findLivresByPriceLessThanOrEqual(double price) {
        try {
            return ((LivreDAO_JPA) livreDAO).findByPriceLessThanOrEqual(price);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
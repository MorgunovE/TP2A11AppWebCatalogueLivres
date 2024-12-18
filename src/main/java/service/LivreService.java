package service;

/**
 *
 * @author Evgenii Morgunov
 */

import DAL.IDAO;
import DAL.LivreDAO_JPA;
import model.Livre;

import java.util.List;

public class LivreService {
    private IDAO<Livre> livreDAO = new LivreDAO_JPA();

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
}
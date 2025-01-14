/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Classe représentant un panier dans le système.
 * @author Evgenii Morgunov
 */

import jakarta.persistence.*;
import java.util.List;

/**
 * Classe représentant un panier dans le système.
 * Chaque panier a un identifiant unique, un utilisateur associé et une liste de livres.
 */
@Entity
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToMany
    @JoinTable(
            name = "Basket_Livre",
            joinColumns = @JoinColumn(name = "basket_id"),
            inverseJoinColumns = @JoinColumn(name = "livre_id")
    )
    private List<Livre> livres;

    /**
     * Constructeur sans argument.
     */
    public Basket() {
    }

    /**
     * Constructeur avec arguments.
     *
     * @param user l'utilisateur associé au panier
     * @param livres la liste de livres dans le panier
     */
    public Basket(User user, List<Livre> livres) {
        this.user = user;
        this.livres = livres;
    }

    /**
     * Obtient l'identifiant du panier.
     *
     * @return l'identifiant du panier
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant du panier.
     *
     * @param id l'identifiant du panier
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtient l'utilisateur associé au panier.
     *
     * @return l'utilisateur associé au panier
     */
    public User getUser() {
        return user;
    }

    /**
     * Définit l'utilisateur associé au panier.
     *
     * @param user l'utilisateur associé au panier
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Obtient la liste de livres dans le panier.
     *
     * @return la liste de livres dans le panier
     */
    public List<Livre> getLivres() {
        return livres;
    }

    /**
     * Définit la liste de livres dans le panier.
     *
     * @param livres la liste de livres dans le panier
     */
    public void setLivres(List<Livre> livres) {
        this.livres = livres;
    }

    @Override
    public String toString() {
        return String.format("Basket{id=%d, user=%s, livres=%s}", id, user, livres);
    }
}
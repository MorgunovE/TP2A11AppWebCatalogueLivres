/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Evgenii Morgunov
 */

import jakarta.persistence.*;

/**
 * Classe représentant un livre dans le système.
 * Chaque livre a un identifiant unique, un titre, une description, un auteur et un genre.
 */
@Entity
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String author;
    private String genre;

    /**
     * Constructeur sans argument.
     */
    public Livre() {
    }

    /**
     * Constructeur avec arguments.
     *
     * @param title le titre du livre
     * @param description la description du livre
     * @param author l'auteur du livre
     * @param genre le genre du livre
     */
    public Livre(String title, String description, String author, String genre) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.genre = genre;
    }

    /**
     * Obtient l'identifiant du livre.
     *
     * @return l'identifiant du livre
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant du livre.
     *
     * @param id l'identifiant du livre
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtient le titre du livre.
     *
     * @return le titre du livre
     */
    public String getTitle() {
        return title;
    }

    /**
     * Définit le titre du livre.
     *
     * @param title le titre du livre
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Obtient la description du livre.
     *
     * @return la description du livre
     */
    public String getDescription() {
        return description;
    }

    /**
     * Définit la description du livre.
     *
     * @param description la description du livre
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtient l'auteur du livre.
     *
     * @return l'auteur du livre
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Définit l'auteur du livre.
     *
     * @param author l'auteur du livre
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Obtient le genre du livre.
     *
     * @return le genre du livre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Définit le genre du livre.
     *
     * @param genre le genre du livre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return String.format("Livre{id=%d, title='%s', description='%s', author='%s', genre='%s'}",
                id, title, description, author, genre);
    }
}
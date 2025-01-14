/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Classe représentant un utilisateur dans le système.
 * @author Evgenii Morgunov
 */

import jakarta.persistence.*;

/**
 * Classe représentant un utilisateur dans le système.
 * Chaque utilisateur a un identifiant unique, un nom, un prénom, un
 * numéro de téléphone et un email.
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String familyName;
    private String tel;
    private String email;

    /**
     * Constructeur sans argument.
     */
    public User() {
    }

    /**
     * Constructeur avec arguments.
     *
     * @param name le nom de l'utilisateur
     * @param familyName le prénom de l'utilisateur
     * @param tel le numéro de téléphone de l'utilisateur
     * @param email l'email de l'utilisateur
     */
    public User(String name, String familyName,
                String tel, String email) {
        this.name = name;
        this.familyName = familyName;
        this.tel = tel;
        this.email = email;
    }

    /**
     * Retourne l'identifiant de l'utilisateur.
     *
     * @return l'identifiant de l'utilisateur
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifie l'identifiant de l'utilisateur.
     *
     * @param id le nouvel identifiant de l'utilisateur
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retourne le nom de l'utilisateur.
     *
     * @return le nom de l'utilisateur
     */
    public String getName() {
        return name;
    }

    /**
     * Modifie le nom de l'utilisateur.
     *
     * @param name le nouveau nom de l'utilisateur
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retourne le prénom de l'utilisateur.
     *
     * @return le prénom de l'utilisateur
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * Modifie le prénom de l'utilisateur.
     *
     * @param familyName le nouveau prénom de l'utilisateur
     */
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    /**
     * Retourne le numéro de téléphone de l'utilisateur.
     *
     * @return le numéro de téléphone de l'utilisateur
     */
    public String getTel() {
        return tel;
    }

    /**
     * Modifie le numéro de téléphone de l'utilisateur.
     *
     * @param tel le nouveau numéro de téléphone de l'utilisateur
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * Retourne l'email de l'utilisateur.
     *
     * @return l'email de l'utilisateur
     */
    public String getEmail() {
        return email;
    }

    /**
     * Modifie l'email de l'utilisateur.
     *
     * @param email le nouvel email de l'utilisateur
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String
                .format("User{id=%d, name='%s', familyName='%s'," +
                                " tel='%s', email='%s'}",
                        id, name, familyName, tel, email);
    }
}
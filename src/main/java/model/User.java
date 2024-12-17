/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 1
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

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
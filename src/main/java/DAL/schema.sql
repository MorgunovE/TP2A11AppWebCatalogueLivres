CREATE DATABASE IF NOT EXISTS CatalogDB;

USE CatalogDB;

CREATE TABLE User (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255),
                      familyName VARCHAR(255),
                      tel VARCHAR(20),
                      email VARCHAR(255)
);

CREATE TABLE Livre (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255),
                       description TEXT,
                       author VARCHAR(255),
                       genre VARCHAR(255),
                       image VARCHAR(255)
);

CREATE TABLE Basket (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        user_id BIGINT,
                        FOREIGN KEY (user_id) REFERENCES User(id)
);

CREATE TABLE Basket_Livre (
                              basket_id BIGINT,
                              livre_id BIGINT,
                              PRIMARY KEY (basket_id, livre_id),
                              FOREIGN KEY (basket_id) REFERENCES Basket(id),
                              FOREIGN KEY (livre_id) REFERENCES Livre(id)
);
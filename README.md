# AppWebCatalogueLivres

## Description
Ce projet est une application web Java qui permet de gérer un catalogue de livres. L'application permet d'ajouter des livres, de les filtrer, de les mettre à jour, de les supprimer, et de gérer les paniers d'achat. Elle prend en charge deux langues : anglais et français.

## Dépôt GitHub
Le code source de ce projet est disponible sur GitHub : [TP2A11AppWebCatalogueLivres](https://github.com/MorgunovE/TP2A11AppWebCatalogueLivres)

Aperçu : [Index](https://morgunove.github.io/TP2A11AppWebCatalogueLivres/webapp/index.html)

Javadoc : [javadoc](https://morgunove.github.io/TP2A11AppWebCatalogueLivres/docs/index.html)

## Fonctionnalités
- Ajout de livres au catalogue.
- Affichage des livres disponibles.
- Affichage des détails d'un livre.
- Ajout de livres au panier d'achat.
- Affichage du panier d'achat.
- Suppression de livres du panier d'achat.
- Filtrage des livres par titre, auteur, genre ou prix.
- Mise à jour et suppression de livres.
- Gestion des utilisateurs.
- Gestion des paniers d'achat.
- Gestion des informations de l'utilisateur.
- Gestion des utilisateurs administrateurs.
- Prise en charge de deux langues : anglais et français.

## Structure du Projet
Le projet est structuré comme suit :
- `src/` : Contient les fichiers source Java.
    - `control/` : Contient les servlets pour gérer les différentes actions.
    - `DAL/` : Contient les classes d'accès aux données.
    - `model/` : Contient les classes de modèle.
    - `service/` : Contient les classes de service.
    - `resources/ressources_i18n/` : Contient les fichiers de ressources pour la traduction.
- `webapp/` : Contient les fichiers web (html, images, jsp, scripts, styles).
    - `styles/` : Contient les fichiers CSS.
    - `images/` : Contient les images.
    - `scripts/` : Contient les fichiers JavaScript.
    - `jsp/` : Contient les JSP pour l'affichage des pages.
    - `html/` : Contient les fichiers HTML.
    - `WEB-INF/` : Contient les fichiers de configuration et les JSP sécurisés.

## Compiler le projet
Pour compiler le projet, utilisez la commande suivante :
```sh
mvn clean install
```

## Exécuter le projet
Pour exécuter le projet, utilisez un serveur Tomcat. Déployez le fichier WAR généré dans le répertoire `webapps` de Tomcat et démarrez le serveur.

## Simulation de scénarios
Le projet inclut des scénarios pour tester les fonctionnalités :
- Ajout de livres.
- Affichage des livres.
- Affichage des détails d'un livre.
- Ajout de livres au panier d'achat.
- Affichage du panier d'achat.
- Suppression de livres du panier d'achat.
- Filtrage et affichage des livres.
- Mise à jour et suppression de livres.
- Gestion des utilisateurs.
- Gestion des informations de l'utilisateur.
- Gestion des utilisateurs administrateurs.
- Gestion des paniers d'achat.
- Mise à jour et suppression de livres.
- Prise en charge de deux langues : anglais et français.

## Vérification
Pour vérifier le bon fonctionnement du projet, exécutez les scénarios de simulation inclus dans les servlets et les JSP.

## Utilisation
1. Clonez le dépôt GitHub.
2. Configurez une base de données MySQL avec les paramètres suivants dans la classe `src/main/java/DAL/CatalogPersistenceUnit.java` :
    ```java
    protected static final String DB_HOST = "localhost";
    protected static final String DB_NAME = "CatalogDB";
    protected static final String DB_USER = "root";
    protected static final String DB_PASS = "root";
    protected static final String DB_PORT = "3306";
    protected static final String DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
    ```
3. Exécutez les scripts `schema.sql` et `dataExample.sql` pour créer et peupler la base de données.
4. Déployez l'application sur un serveur Tomcat.
5. Accédez à l'application via un navigateur web.

## Navigation dans l'application
- **Catalogue** : Affiche la liste des livres disponibles.
- **Compte** : Permet de gérer les informations de l'utilisateur.
- **Paiement** : Permet de gérer les livres ajoutés au panier.
- **Administration** : Permet d'ajouter, mettre à jour et supprimer des livres.

## Auteur
Ce projet a été réalisé par [Evgenii Morgunov](https://github.com/MorgunovE/) et [Rustam Zholdubayev](https://github.com/RustamZhol/)

## Licence
Ce projet est sous licence MIT.
package DAL;

/**
 *
 * @author Evgenii Morgunov
 */

/**
 * SQL_BOX class.
 */
public class SQL_BOX {
    // sql pour trouver tous les utilisateurs
    public static final String FIND_ALL_USERS_JPQL =
            "SELECT u FROM User u";

    // sql pour trouver un utilisateur par son nom et prénom
    public static final String
            FIND_USER_BY_NAME_AND_FAMILY_NAME_JPQL =
            "SELECT u FROM User u WHERE u.name =" +
                    " :name AND u.familyName = :familyName";

    // sql pour trouver un utilisateur par son numéro de téléphone
    public static final String FIND_USER_BY_TEL_JPQL =
            "SELECT u FROM User u WHERE u.tel = :tel";

    // sql pour trouver un utilisateur par son courriel
    public static final String FIND_USER_BY_EMAIL_JPQL =
            "SELECT u FROM User u WHERE u.email = :email";

    // sql pour trouver tous les paniers
    public static final String FIND_ALL_BASKETS_JPQL =
            "SELECT b FROM Basket b";

    // sql pour trouver un panier par son identifiant
    public static final String FIND_BASKET_BY_USER_ID_JPQL =
            "SELECT b FROM Basket b WHERE b.user.id = :userId";

    // sql pour trouver un panier par son identifiant
    public static final String FIND_ALL_LIVRES_JPQL =
            "SELECT l FROM Livre l";

    // sql pour trouver un livre par son titre
    public static final String FIND_LIVRE_BY_TITLE_JPQL =
            "SELECT l FROM Livre l WHERE l.title = :title";

    // sql pour trouver un livre par son auteur
    public static final String FIND_LIVRE_BY_AUTHOR_JPQL =
            "SELECT l FROM Livre l WHERE l.author = :author";

    // sql pour trouver un livre par son genre
    public static final String FIND_LIVRE_BY_GENRE_JPQL =
            "SELECT l FROM Livre l WHERE l.genre = :genre";

    // sql pour trouver un livre par son prix
    public static final String
            FIND_LIVRE_BY_PRICE_LESS_THAN_OR_EQUAL_JPQL =
            "SELECT l FROM Livre l WHERE l.price <= :price";
}
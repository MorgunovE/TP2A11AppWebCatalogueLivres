package DAL;

/**
 * Persistence Unit pour le Catalogue de la bibliothèque
 * @author Evgenii Morgunov
 */

import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import jakarta.persistence.spi.ClassTransformer;
import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.persistence.spi.PersistenceUnitTransactionType;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import javax.sql.DataSource;

/**
 * Persistence Unit pour le Catalogue
 */
public class CatalogPersistenceUnit implements PersistenceUnitInfo {
    protected static final String DB_HOST = "localhost";
    protected static final String DB_NAME = "CatalogDB";
    protected static final String DB_USER = "root";
    protected static final String DB_PASS = "root";
    protected static final String DB_PORT = "3306";
    protected static final String DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;

    /**
     * Retourne le nom de l'unité de persistance
     * @return le nom de l'unité de persistance
     */
    @Override
    public String getPersistenceUnitName() {
        return "CatalogPersistenceUnit";
    }

    /**
     * Retourne le nom du fournisseur de persistance
     * @return le nom du fournisseur de persistance
     */
    @Override
    public String getPersistenceProviderClassName() {
        return "org.hibernate.jpa.HibernatePersistenceProvider";
    }

    /**
     * Retourne le type de transaction
     * @return le type de transaction
     */
    @Override
    public PersistenceUnitTransactionType getTransactionType() {
        return PersistenceUnitTransactionType.RESOURCE_LOCAL;
    }

    /**
     * Retourne la source de données JTA
     * @return la source de données JTA
     */
    @Override
    public DataSource getJtaDataSource() {
        return null;
    }

    /**
     * Retourne la source de données non JTA
     * @return la source de données non JTA
     */
    @Override
    public DataSource getNonJtaDataSource() {
        return null;
    }

    /**
     * Retourne la liste des noms de fichiers de mapping
     * @return la liste des noms de fichiers de mapping
     */
    @Override
    public List<String> getMappingFileNames() {
        return Collections.emptyList();
    }

    /**
     * Retourne la liste des URL des fichiers JAR
     * @return la liste des URL des fichiers JAR
     */
    @Override
    public List<URL> getJarFileUrls() {
        return Collections.emptyList();
    }

    /**
     * Retourne l'URL de la racine de l'unité de persistance
     * @return l'URL de la racine de l'unité de persistance
     */
    @Override
    public URL getPersistenceUnitRootUrl() {
        return null;
    }

    /**
     * Retourne la liste des noms de classes gérées
     * @return la liste des noms de classes gérées
     */
    @Override
    public List<String> getManagedClassNames() {
        List<String> managedClasses = new ArrayList<>();
        managedClasses.add("model.User");
        managedClasses.add("model.Livre");
        managedClasses.add("model.Basket");
        return managedClasses;
    }

    /**
     * Retourne si les classes non listées sont exclues
     * @return si les classes non listées sont exclues
     */
    @Override
    public boolean excludeUnlistedClasses() {
        return false;
    }

    /**
     * Retourne le mode de cache partagé
     * @return le mode de cache partagé
     */
    @Override
    public SharedCacheMode getSharedCacheMode() {
        return SharedCacheMode.UNSPECIFIED;
    }

    /**
     * Retourne le mode de validation
     * @return le mode de validation
     */
    @Override
    public ValidationMode getValidationMode() {
        return null;
    }

    /**
     * Retourne les propriétés
     * @return les propriétés
     */
    @Override
    public Properties getProperties() {
        Properties properties = new Properties();

        // JDBC properties
        properties.setProperty("jakarta.persistence.jdbc.url", DB_URL);
        properties.setProperty("jakarta.persistence.jdbc.user", DB_USER);
        properties.setProperty("jakarta.persistence.jdbc.password", DB_PASS);
        properties.setProperty("jakarta.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
        properties.setProperty("jakarta.persistence.schema-generation.database.action", "update");

        // Hibernate properties
        properties.setProperty("hibernate.format_sql", "false");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.use_sql_comments", "false");

        return properties;
    }

    /**
     * Retourne la version du schéma XML de persistance
     * @return la version du schéma XML de persistance
     */
    @Override
    public String getPersistenceXMLSchemaVersion() {
        return "3.0";
    }

    /**
     * Retourne le class loader
     * @return le class loader
     */
    @Override
    public ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * Ajoute un transformateur de classe
     * @param transformer le transformateur de classe
     */
    @Override
    public void addTransformer(ClassTransformer transformer) {
        // No implementation needed
    }

    /**
     * Retourne un nouveau class loader temporaire
     * @return un nouveau class loader temporaire
     */
    @Override
    public ClassLoader getNewTempClassLoader() {
        return null;
    }
}
package DAL;

/**
 *
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

public class CatalogPersistenceUnit implements PersistenceUnitInfo {
    protected static final String DB_HOST = "localhost";
    protected static final String DB_NAME = "CatalogDB";
    protected static final String DB_USER = "root";
    protected static final String DB_PASS = "root";
    protected static final String DB_PORT = "3306";
    protected static final String DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;

    @Override
    public String getPersistenceUnitName() {
        return "CatalogPersistenceUnit";
    }

    @Override
    public String getPersistenceProviderClassName() {
        return "org.hibernate.jpa.HibernatePersistenceProvider";
    }

    @Override
    public PersistenceUnitTransactionType getTransactionType() {
        return PersistenceUnitTransactionType.RESOURCE_LOCAL;
    }

    @Override
    public DataSource getJtaDataSource() {
        return null;
    }

    @Override
    public DataSource getNonJtaDataSource() {
        return null;
    }

    @Override
    public List<String> getMappingFileNames() {
        return Collections.emptyList();
    }

    @Override
    public List<URL> getJarFileUrls() {
        return Collections.emptyList();
    }

    @Override
    public URL getPersistenceUnitRootUrl() {
        return null;
    }

    @Override
    public List<String> getManagedClassNames() {
        List<String> managedClasses = new ArrayList<>();
        managedClasses.add("model.User");
        managedClasses.add("model.Livre");
        managedClasses.add("model.Basket");
        return managedClasses;
    }

    @Override
    public boolean excludeUnlistedClasses() {
        return false;
    }

    @Override
    public SharedCacheMode getSharedCacheMode() {
        return SharedCacheMode.UNSPECIFIED;
    }

    @Override
    public ValidationMode getValidationMode() {
        return null;
    }

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

    @Override
    public String getPersistenceXMLSchemaVersion() {
        return "3.0";
    }

    @Override
    public ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    @Override
    public void addTransformer(ClassTransformer transformer) {
        // No implementation needed
    }

    @Override
    public ClassLoader getNewTempClassLoader() {
        return null;
    }
}
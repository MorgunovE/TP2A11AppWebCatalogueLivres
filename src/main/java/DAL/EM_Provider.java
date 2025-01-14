/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 * EM_Provider class for entity manager provider singleton
 * @author Evgenii Morgunov
 */

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.spi.PersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;

/**
 * Classe EM_Provider.
 */
public class EM_Provider {

    /**
     * L'instance de la classe.
     */
    private static EM_Provider instance = null;

    /**
     * L'entity manager factory.
     */
    private EntityManagerFactory factory = null;

    /**
     * Constructeur de la classe EM_Provider.
     */
    private EM_Provider() {
        init_usingPUDefinedInClass();
    }

    /**
     * Initialise l'entity manager factory en utilisant l'unité de persistance définie dans le fichier persistence.xml.
     */
    private void init_usingPUDefinedInClass() {
        PersistenceUnitInfo catalogPUInfo = new CatalogPersistenceUnit();
        this.factory = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(catalogPUInfo, new HashMap<>());
    }

    /**
     * Retourne l'instance de la classe.
     *
     * @return l'instance de la classe
     */
    public static EM_Provider getInstance() {
        if (instance == null) {
            synchronized (EM_Provider.class) {
                if (instance == null) {
                    instance = new EM_Provider();
                }
            }
        }
        return instance;
    }

    /**
     * Retourne l'entity manager.
     *
     * @return l'entity manager
     */
    public EntityManager getEntityManager() {
        return this.factory.createEntityManager();
    }
}
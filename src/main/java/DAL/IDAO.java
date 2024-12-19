package DAL;

/**
 *
 * @author Evgenii Morgunov
 */

import java.util.List;

/**
 * Interface for Data Access Object
 * @param <T> entity type
 */
public interface IDAO<T> {

    /**
     * Create an entity
     * @param entity the entity to create
     */
    void create(T entity);

    /**
     * Find an entity by its id
     * @param id the id of the entity
     * @return the entity found, or null in case of error
     */
    T findById(Long id);

    /**
     * Find all entities
     * @return the list of all entities, or null in case of error
     */
    List<T> findAll();

    /**
     * Update an entity
     * @param entity the entity to update
     */
    void update(T entity);

    /**
     * Delete an entity
     * @param entity the entity to delete
     */
    void delete(T entity);
}
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
    void create(T entity);
    T findById(Long id);
    List<T> findAll();
    void update(T entity);
    void delete(T entity);
}
package fr.lefort.business;

import fr.lefort.entities.Tasks;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IBusiness<T> {
  /**
   * Method to add or update an item in database.
   * @param t
   * @throws Exception
   */
  public void saveOrUpdateOne(T t) throws Exception;

  /**
   * Delete one item by its id.
   * @param t
   * @throws Exception
   */
  public void deleteOne(T t) throws Exception;

  /**
   * List of all items
   * @return item
   */
  public List<T> findAll();

  /**
   * Get one item from database
   *
   * @param id
   * @return item
   */
  public Optional<T> readOneById(Long id);
}

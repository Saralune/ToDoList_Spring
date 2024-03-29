/**
 *
 */
package fr.lefort.business;

import fr.lefort.entities.Category;
import fr.lefort.entities.Tasks;
import fr.lefort.entities.Users;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Saralune
 *
 */
public interface IBusiness {
  /**
   * Find all tasks where description contains keyword, by user. Return a list of tasks, organised by pages.
   * @param keyword
   * @param page
   * @param tasksByPage
   * @return Tasks By Page
   * @throws Exception
   */
  Page<Tasks> readByDescriptionContains(String keyword, int page, int tasksByPage, Users users) throws Exception;

  /**
   * Return a list of categories, by user, in database.
   * @return
   * @throws Exception
   */
  public List<Category> findAllCategoriesByUsers(Users users) throws Exception;

  /**
   * Method to add or update a task in database.
   * @param task
   * @throws Exception
   */
  public void saveOrUpdateTask(Tasks task) throws Exception;

  /**
   * Method to add or update a category in database.
   * @param category
   * @throws Exception
   */
  public void saveOrUpdateCategory(Category category) throws Exception;

  /**
   * Delete Task by its id.
   * @param id
   * @throws Exception
   */
  public void deleteTask(Long id) throws Exception;

  /**
   * Delete category by its id.
   * @param id
   * @throws Exception
   */
  public void deleteCategory(Long id) throws Exception;

  /**
   * Get task by its id.
   * @param id
   * @return Task
   * @throws Exception
   */
  public Tasks readTasksById(Long id) throws Exception;

  /**
   * Get Category by its id.
   * @param id
   * @return Category
   * @throws Exception
   */
  public Category readCategoryById(Long id) throws Exception;

  /**
   * List of tasks by Category, organised by pages.
   * @param id
   * @param page
   * @param tasksByPage
   * @return Tasks by page
   * @throws Exception
   */
  public Page<Tasks> readTasksByCategory(Long id, int page, int tasksByPage) throws Exception ;

  /**
   * Get user by its mail.
   * @param mail
   * @return Users
   * @throws Exception
   */
  public Users getUserByMail(String mail) throws Exception;
}

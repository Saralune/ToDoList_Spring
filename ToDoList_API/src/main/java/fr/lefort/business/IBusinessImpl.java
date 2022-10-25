
package fr.lefort.business;

import fr.lefort.entities.Category;
import fr.lefort.entities.Tasks;
import fr.lefort.entities.Users;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IBusinessImpl implements IBusiness {

  @Override
  public Page<Tasks> readByDescriptionContains(String keyword, int page, int tasksByPage, Users users) throws Exception {
    return null;
  }

  @Override
  public List<Category> findAllCategoriesByUsers(Users users) throws Exception {
    return null;
  }

  @Override
  public void saveOrUpdateTask(Tasks task) throws Exception {

  }

  @Override
  public void saveOrUpdateCategory(Category category) throws Exception {

  }

  @Override
  public void deleteTask(Long id) throws Exception {

  }

  @Override
  public void deleteCategory(Long id) throws Exception {

  }

  @Override
  public Tasks readTasksById(Long id) throws Exception {
    return null;
  }

  @Override
  public Category readCategoryById(Long id) throws Exception {
    return null;
  }

  @Override
  public Page<Tasks> readTasksByCategory(Long id, int page, int tasksByPage) throws Exception {
    return null;
  }

  @Override
  public Users getUserByMail(String mail) throws Exception {
    return null;
  }
}

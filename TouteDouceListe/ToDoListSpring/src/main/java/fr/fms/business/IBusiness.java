/**
 * 
 */
package fr.fms.business;

import java.util.List;

import org.springframework.data.domain.Page;

import fr.fms.entities.Category;
import fr.fms.entities.Task;
import fr.fms.entities.Users;

/**
 * @author Stagiaires10P
 *
 */
public interface IBusiness {
	public Page<Task> readByDescriptionContains(String keyword, int page, int tasksByPage) throws Exception;
	public List<Category> findAllCategories() throws Exception;
	
	public void saveOrUpdateTask(Task task) throws Exception;
	public void saveOrUpdateCategory(Category category) throws Exception;
	
	public void deleteTask(Long id) throws Exception;
	public void deleteCategory(Long id) throws Exception;
	
	public Task readTasksById(Long id) throws Exception;
	
	public Category readCategoryById(Long id) throws Exception;
	public Page<Task> readTasksByCategory(Long id, int page, int tasksByPage) throws Exception ;
	
	public Users getUserByMail(String mail) throws Exception;
}

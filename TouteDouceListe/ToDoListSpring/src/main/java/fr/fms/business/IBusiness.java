/**
 * 
 */
package fr.fms.business;

import java.util.List;

import org.springframework.data.domain.Page;

import fr.fms.entities.Category;
import fr.fms.entities.Task;

/**
 * @author Stagiaires10P
 *
 */
public interface IBusiness {
	public Page<Task> readByDescriptionContains(String keyword, int page, int tasksByPage);
	public List<Category> findAllCategories();
	
	public void saveOrUpdateTask(Task task);
	public void saveOrUpdateCategory(Category category);
	
	public void deleteTask(Long id) throws Exception;
	public void deleteCategory(Long id) throws Exception;
	
	public Task readTasksById(Long id);
	
	public Category readCategoryById(Long id);
	public Page<Task> readTasksByCategory(Long id, int page, int tasksByPage) throws Exception ;
}

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
	
	public void saveTask(Task task);
	public void saveCategory(Category category);
	
	public void deleteTask(Long id);
	public void deleteCategory(Long id);
}

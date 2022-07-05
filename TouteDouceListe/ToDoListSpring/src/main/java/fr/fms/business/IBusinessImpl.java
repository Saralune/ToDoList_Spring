/**
 * 
 */
package fr.fms.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.fms.dao.CategoryRepository;
import fr.fms.dao.TaskRepository;
import fr.fms.entities.Task;
import fr.fms.entities.Category;


/**
 * @author Stagiaires10P
 *
 */
@Service
public class IBusinessImpl implements IBusiness {
	
	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Page<Task> readByDescriptionContains(String keyword, int page, int tasksByPage) {
		return taskRepository.findByDescriptionContains(keyword, PageRequest.of(page, tasksByPage));
	}

	@Override
	public List<Category> findAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public void saveTask(Task task) {
		taskRepository.save(task);
	}

	@Override
	public void saveCategory(Category category) {
		categoryRepository.save(category);		
	}

	@Override
	public void deleteTask(Long id) {
		taskRepository.deleteById(id);		
	}
	
	@Override
	public void deleteCategory(Long id) {
		categoryRepository.deleteById(id);		
	}
}

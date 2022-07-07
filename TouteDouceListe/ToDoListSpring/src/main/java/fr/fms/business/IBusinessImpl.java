/**
 * 
 */
package fr.fms.business;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.fms.dao.CategoryRepository;
import fr.fms.dao.TaskRepository;
import fr.fms.dao.UsersRepository;
import fr.fms.entities.Task;
import fr.fms.entities.Users;
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
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	EntityManager entityManager;

	@Override
	public Page<Task> readByDescriptionContains(String keyword, int page, int tasksByPage) throws Exception {       
		return taskRepository.findByDescriptionContains(keyword, PageRequest.of(page, tasksByPage));
	}

	@Override
	public List<Category> findAllCategories() throws Exception {
		return categoryRepository.findAll();
	}

	@Override
	public void saveOrUpdateTask(Task task) throws Exception {
		taskRepository.save(task);
	}

	@Override
	public void saveOrUpdateCategory(Category category) throws Exception {
		categoryRepository.save(category);		
	}

	@Override
	public void deleteTask(Long id) throws Exception {
		taskRepository.deleteById(id);		
	}
	
	@Override
	public void deleteCategory(Long id) throws Exception {
		categoryRepository.deleteById(id);		
	}

	@Override
	public Page<Task> readTasksByCategory(Long id, int page, int tasksByPage) throws Exception {
		return taskRepository.findByCategoryId(id, PageRequest.of(page, tasksByPage));
	}

	@Override
	public Category readCategoryById(Long id) throws Exception {
		return categoryRepository.findById(id).get();
	}

	@Override
	public Task readTasksById(Long id) throws Exception {
		return taskRepository.findById(id).get();
	}

	@Override
	public Users getUserByMail(String mail) throws Exception {
		Users userReceived = usersRepository.findByMail(mail);
		Users user = new Users(userReceived.getId(), userReceived.getMail(), userReceived.getPassword(),
				userReceived.getActive());

		return user;
	}

}

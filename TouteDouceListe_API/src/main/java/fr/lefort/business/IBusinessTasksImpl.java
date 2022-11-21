package fr.lefort.business;

import fr.lefort.dao.TasksRepository;
import fr.lefort.entities.Category;
import fr.lefort.entities.Tasks;
import fr.lefort.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IBusinessTasksImpl implements IBusiness<Tasks> {
  @Autowired
  TasksRepository tasksRepository;

  @Override
  public void saveOrUpdateOne(Tasks task) throws Exception {
    tasksRepository.save(task);
  }

  @Override
  public void deleteOne(Tasks task) throws Exception {
    tasksRepository.delete(task);
  }

  @Override
  public List findAll() {
    return tasksRepository.findAllByUsers(new Users());
  }

  public List<Tasks> findAllTasksByUser(Users user) {
    return tasksRepository.findAllByUsers(user);
  }


//  public Page<Tasks> readByDescriptionContains(String keyword, int page, int tasksByPage, Users users) throws Exception {
//    return tasksRepository.findByDescriptionContainsAndUsers(keyword, PageRequest.of(page, tasksByPage), users);
//  }

  @Override
  public Optional<Tasks> readOneById(Long id) {
    return tasksRepository.findById(id);
  }

  public List<Tasks> readTasksByDescriptionContains(String string){
    return tasksRepository.findByDescriptionContains(string);
  }

  public List<Tasks> readTasksByCatId(Long catId){
    return tasksRepository.findByCategoryId(catId);
  }
}

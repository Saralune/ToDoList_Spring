package fr.lefort.business;

import fr.lefort.dao.TasksRepository;
import fr.lefort.entities.Tasks;
import fr.lefort.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
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

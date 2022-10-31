package fr.lefort.web;

import fr.lefort.business.IBusinessTasksImpl;
import fr.lefort.entities.Tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/task")
public class TasksController {

  @Autowired
  private IBusinessTasksImpl iBusiness;

  @GetMapping("/all")
  public List<Tasks> allUserTasks(){
    return iBusiness.findAll();
  }

  @GetMapping("/{id}")
  public Tasks getUserTask(@PathVariable("id") long id) throws Exception {
    Optional<Tasks> tasks = iBusiness.readOneById(id);
    if (tasks.isPresent()) {
      return tasks.get();
    }
    return null;
  }

  @PostMapping("/saveTask")
  public void saveTask(@RequestBody Tasks tasks) throws Exception {
    iBusiness.saveOrUpdateOne(tasks);
  }

  @GetMapping("/research/{description}")
  public List<Tasks> getTasksBySearch(@PathVariable("description") String description) {
    List<Tasks> tasksBySearch = iBusiness.readTasksByDescriptionContains(description);
    return tasksBySearch;
  }

  @DeleteMapping("/deleteTask/{id}")
  public void deleteTraining(@PathVariable("id") Long id) throws Exception {
    iBusiness.deleteOne(iBusiness.readOneById(id).get());
  }

  @GetMapping ("/category/{id}")
  public List<Tasks> getTasksByCatId(@PathVariable("id") Long id){
    return iBusiness.readTasksByCatId(id);
  }
}

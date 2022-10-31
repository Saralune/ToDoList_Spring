package fr.lefort.business;

import fr.lefort.entities.Tasks;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IBusinessTasksImpl implements IBusiness<Tasks> {
  @Override
  public void saveOrUpdateOne(Tasks task) throws Exception {

  }

  @Override
  public void deleteOne(Tasks task) throws Exception {

  }

  @Override
  public List findAll() {
    return null;
  }

  @Override
  public Optional<Tasks> readOneById(Long id) {
    return null;
  }

  public List<Tasks> readTasksByDescriptionContains(String string){
    return null;
  }

  public List<Tasks> readTasksByCatId(Long catId){
    return null;
  }
}

package fr.lefort.business;

import fr.lefort.entities.Category;
import fr.lefort.entities.Tasks;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IBusinessCategoryImpl implements IBusiness<Category>{

  @Override
  public void saveOrUpdateOne(Category category) throws Exception {

  }

  @Override
  public void deleteOne(Category category) throws Exception {

  }

  @Override
  public List findAll() {
    return null;
  }

  @Override
  public Optional<Category> readOneById(Long id) {
    return null;
  }
}

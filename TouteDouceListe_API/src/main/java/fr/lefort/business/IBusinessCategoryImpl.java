package fr.lefort.business;

import fr.lefort.dao.CategoryRepository;
import fr.lefort.entities.Category;
import fr.lefort.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IBusinessCategoryImpl implements IBusiness<Category>{

  @Autowired
  CategoryRepository categoryRepository;

  @Override
  public void saveOrUpdateOne(Category category) throws Exception {
    categoryRepository.save(category);
  }

  @Override
  public void deleteOne(Category category) throws Exception {
    categoryRepository.delete(category);
  }

  @Override
  public List<Category> findAll() {
    return categoryRepository.findAll();
  }
  public List<Category> findAllCatByUser(Users user) {
    return categoryRepository.findAllByUsers(user);
  }

  @Override
  public Optional<Category> readOneById(Long id) {
    return categoryRepository.findById(id);
  }
}

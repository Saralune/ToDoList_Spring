package fr.lefort.web;

import fr.lefort.business.IBusinessCategoryImpl;
import fr.lefort.entities.Category;
import fr.lefort.entities.Tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/category")
public class CategoryController {

  @Autowired
  private IBusinessCategoryImpl iBusiness;

  @GetMapping("/all")
  public List<Category> allCategories(){
    System.out.println("okkkkkk");
    return iBusiness.findAll();
  }

  @GetMapping("/{id}")
  public Category getCategory(@PathVariable("id") long id) throws Exception {
    Optional<Category> category = iBusiness.readOneById(id);
    if (category.isPresent()) {
      return category.get();
    }
    return null;
  }
  @PostMapping("/save")
  public void saveTask(@RequestBody Category category) throws Exception {
    iBusiness.saveOrUpdateOne(category);
  }

  @DeleteMapping("/deleteCategory/{id}")
  public void deleteTraining(@PathVariable("id") Long id) throws Exception {
    iBusiness.deleteOne(iBusiness.readOneById(id).get());
  }
}

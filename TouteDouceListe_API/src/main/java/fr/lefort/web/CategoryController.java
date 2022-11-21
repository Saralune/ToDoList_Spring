package fr.lefort.web;

import fr.lefort.business.IBusinessCategoryImpl;
import fr.lefort.business.IBusinessUsersImpl;
import fr.lefort.entities.Category;
import fr.lefort.entities.Users;
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

  @Autowired
  private IBusinessUsersImpl iUsersBusiness;

  @GetMapping("/{id}/all")
  public List<Category> allCategories(@PathVariable("id") long idUser) throws Exception {
    Optional<Users> user = iUsersBusiness.readOneById(idUser);
    if(user.isPresent()) return iBusiness.findAllCatByUser(user.get());
    return null;
  }

  @GetMapping("/{id}")
  public Category getCategory(@PathVariable("id") long id) throws Exception {
    Optional<Category> category = iBusiness.readOneById(id);
    //Users user =

    // && category.get().getUsers().getMail() == user.mail
    if (category.isPresent()) {
      return category.get();
    }
    return null;
  }
  @PostMapping("/save")
  public void saveCategory(@RequestBody Category category) throws Exception {
    try {
      //ajout de l'user avant sauvegarde
      System.out.println("saved cat");
      iBusiness.saveOrUpdateOne(category);

    } catch (Exception e){
      System.out.println(e.getMessage());
    }
  }

  @DeleteMapping("/deleteCategory/{id}")
  public void deleteCategory(@PathVariable("id") Long id) throws Exception {
    iBusiness.deleteOne(iBusiness.readOneById(id).get());
  }
}

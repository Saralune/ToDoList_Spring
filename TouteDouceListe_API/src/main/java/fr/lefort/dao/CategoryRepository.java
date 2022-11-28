package fr.lefort.dao;

import fr.lefort.entities.Category;
import fr.lefort.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  public List<Category> findAllByUsers(Users user);
}

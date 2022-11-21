package fr.lefort.dao;

import fr.lefort.entities.Tasks;
import fr.lefort.entities.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long> {
  List<Tasks> findByDescriptionContains (String description);
  //Page<Tasks> findByDescriptionContainsAndUsers (String keyword, Pageable pageable, Users users);

  List<Tasks> findAll();

  List<Tasks> findByCategoryId(Long catId);

  List<Tasks> findAllByUsers(Users users);
}

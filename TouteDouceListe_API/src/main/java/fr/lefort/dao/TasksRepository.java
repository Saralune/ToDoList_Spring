package fr.lefort.dao;

import fr.lefort.entities.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TasksRepository extends JpaRepository<Tasks, Long> {
  List<Tasks> findByDescriptionContains (String description);

  List<Tasks> findAll();

  List<Tasks> findByCategoryId(Long catId);
}

/**
 * 
 */
package fr.fms.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.Task;

/**
 * @author Stagiaires10P
 *
 */
public interface TaskRepository  extends JpaRepository<Task, Long> {
	Page<Task> findByDescriptionContains(String keyword, Pageable pageable);
	Page<Task> findByDescriptionContainsAndDeletedFalse(String keyword, Pageable pageable);
	Page<Task> findByCategoryId(Long categoryId, Pageable pageable);
}

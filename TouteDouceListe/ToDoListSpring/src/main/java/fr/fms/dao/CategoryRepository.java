/**
 * 
 */
package fr.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.Category;

/**
 * @author Stagiaires10P
 *
 */
public interface CategoryRepository  extends JpaRepository<Category, Long> {

}

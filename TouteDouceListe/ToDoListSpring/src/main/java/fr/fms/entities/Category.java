/**
 * 
 */
package fr.fms.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Stagiaires10P
 *
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 2, max = 50, message = "La taille doit être comprise entre 2 et 50 caractères.")
	private String name;
	
	@OneToMany(mappedBy = "category")
	private Collection<Task> tasks;
	
	@ManyToOne
	private Users users;

	/**
	 * @param id
	 * @param name
	 */
	public Category(Long id,
			@NotNull @Size(min = 2, max = 50, message = "La taille doit être comprise entre 2 et 50 caractères.") String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * @param name
	 */
	public Category(
			@NotNull @Size(min = 2, max = 50, message = "La taille doit être comprise entre 2 et 50 caractères.") String name) {
		this.name = name;
	}
}

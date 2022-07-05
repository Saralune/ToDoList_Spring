/**
 * 
 */
package fr.fms.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Stagiaires10P
 *
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SQLDelete(sql = "UPDATE task SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Task {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 2, max = 20, message = "La taille doit être comprise entre 2 et 50 caractères.")
	private String nameTask;

	@NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dateTask;
	
	@NotNull
	@Size(min = 2, max = 100, message = "La taille doit être comprise entre 2 et 100 caractères.")
	private String description;
	
	//TODO FAIRE UNE CLASSE STATUT ??
//	@ManyToOne
//	private String status;
	
	private boolean checked;
	
	private boolean deleted = Boolean.FALSE;
	
	@ManyToOne
	private Category category;
}

package fr.lefort.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tasks {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Size(min = 2, max = 20, message = "La taille doit être comprise entre 2 et 50 caractères.")
  private String nameTask;

  @NotNull
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date dateTask;

  @NotNull
  @Size(min = 2, max = 100, message = "La taille doit être comprise entre 2 et 100 caractères.")
  private String description;

  @NotNull
  private boolean checked;

  @ManyToOne(cascade = {CascadeType.ALL})
  private Category category;

  @ManyToOne
  private Users users;

/*  public Tasks(Long id, String nameTask, Date dateTask, String description, boolean checked, Category category, Users users) {
    this.id = id;
    this.nameTask = nameTask;
    this.dateTask = dateTask;
    this.description = description;
    this.checked = checked;
    this.category = category;
    this.users = users;
  }*/
}

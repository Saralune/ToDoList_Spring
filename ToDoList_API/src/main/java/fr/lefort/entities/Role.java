package fr.lefort.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String role;



  @ManyToMany
  @JoinTable(
    name = "users_role",
    joinColumns = {@JoinColumn(name = "roleId")},
    inverseJoinColumns = {@JoinColumn(name = "usersId")}
  )
  @JsonIgnore
  private List<Users> users;

  /**
   * @param id
   * @param role
   */
  public Role(Long id, String role) {
    this.id = id;
    this.role = role;
  }
  public Role( String role) {
    this.role = role;
  }
}


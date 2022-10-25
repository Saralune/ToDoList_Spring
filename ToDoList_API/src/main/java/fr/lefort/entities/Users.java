package fr.lefort.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  //@Email
  private String mail;

  @NotNull
  private String password;

  @NotNull
  private String username;

  @OneToMany(mappedBy = "users")
  @JsonIgnore
  private Collection<Tasks> task;

//	@OneToMany(mappedBy = "users")@JsonIgnore
//	private Collection<Category> category;

  @NotNull
  private Boolean active;

  @ManyToMany
  @JoinTable(
    name = "users_role",
    joinColumns = {@JoinColumn(name = "usersId")},
    inverseJoinColumns = {@JoinColumn(name = "roleId")}
  )
  @JsonIgnore
  private List<Role> role;

  /**
   * @param id
   * @param password
   */
  public Users(Long id, String mail, String password) {
    this.id = id;
    this.mail = mail;
    this.password = password;
  }

  /**
   * @param id
   * @param mail
   * @param active
   */
  public Users(Long id, String mail, @NotNull Boolean active) {
    this.id = id;
    this.mail = mail;
    this.active = active;
  }

  /**
   * @param id
   * @param mail
   * @param password
   * @param active
   */
  public Users(Long id, @NotNull String mail, @NotNull String password, @NotNull Boolean active) {
    this.id = id;
    this.mail = mail;
    this.password = password;
    this.active = active;
  }


  /**
   * @param id
   * @param mail
   */
  public Users(Long id, @NotNull String mail) {
    this.id = id;
    this.mail = mail;
  }
  public Users(Long id, String username, @NotNull String mail, String password, Boolean active, List<Role> role) {
    this.id = id;
    this.username=username;
    this.mail = mail;
    this.password=password;
    this.active=active;
    this.role=role;
  }
  public void setRoles(List<Role> roles) {
    this.role=roles;
  }

  public List<Role> getRoles() {

    return role;
  }
}

package fr.lefort.dao;

import fr.lefort.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
  public Optional<Users> findByMail(String mail);

  Optional<Users> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByMail(String mail);

  //public Users save(Users users);
}

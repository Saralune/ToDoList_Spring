package fr.lefort.dao;

import fr.lefort.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
  List<Role> findAll();

  Optional<Role> findByRole(String role);
}

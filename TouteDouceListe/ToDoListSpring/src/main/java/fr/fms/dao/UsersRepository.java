/**
 * 
 */
package fr.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.Users;

/**
 * @author Stagiaires10P
 *
 */
public interface UsersRepository extends JpaRepository<Users, Long>  {
	public Users findByMail(String mail);
}

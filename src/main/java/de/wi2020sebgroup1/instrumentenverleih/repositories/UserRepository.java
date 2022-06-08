package de.wi2020sebgroup1.instrumentenverleih.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import de.wi2020sebgroup1.instrumentenverleih.entities.User;

public interface UserRepository extends CrudRepository<User, UUID>{
	
	Optional<User> findByUsername(String username);
	
	Optional<User> findByUsernameEquals(String username);
	
	Optional<User> findByEmailEquals(String email);
	
	@Query("select u from User u where u.username =?1")
	User findByUsernameSpecial(String username);

	Optional<User> findByEmail(String email);

}

package de.wi2020sebgroup1.instrumentenverleih.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import de.wi2020sebgroup1.instrumentenverleih.entities.Role;

public interface RoleRepository extends CrudRepository<Role, UUID> {
	public Optional<Role> findByAuthorization(String authorization);
}

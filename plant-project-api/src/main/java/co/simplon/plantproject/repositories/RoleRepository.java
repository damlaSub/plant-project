package co.simplon.plantproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.plantproject.entities.Role;

public interface RoleRepository
	extends JpaRepository<Role, Long> {

    Role getReferenceByCode(String name);
}

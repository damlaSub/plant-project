package co.simplon.plantproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.plantproject.entities.Role;

public interface RoleRepository
	extends JpaRepository<Role, String> {

    Role getReferenceByCode(String name);
}

package co.simplon.plantproject.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.plantproject.dtos.RoleValue;
import co.simplon.plantproject.entities.Role;

public interface RoleRepository
	extends JpaRepository<Role, String> {

    Collection<RoleValue> findAllProjectedBy();

    Role findByCode(String name);
}

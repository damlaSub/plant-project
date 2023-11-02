package co.simplon.plantproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.plantproject.entities.User;

public interface UserRepository
	extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);

    User getByEmail(String email);
}

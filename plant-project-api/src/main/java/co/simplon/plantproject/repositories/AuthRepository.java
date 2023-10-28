package co.simplon.plantproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.plantproject.entities.User;

public interface AuthRepository
	extends JpaRepository<User, Long> {

    User getByEmail(String email);
}

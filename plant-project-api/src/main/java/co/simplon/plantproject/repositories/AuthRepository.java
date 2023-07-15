package co.simplon.plantproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.plantproject.entities.Account;

public interface AuthRepository
	extends JpaRepository<Account, Long> {

    Account getByEmail(String email);
}

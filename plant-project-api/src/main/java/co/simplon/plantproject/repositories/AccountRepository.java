package co.simplon.plantproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.plantproject.entities.Account;

public interface AccountRepository
	extends JpaRepository<Account, Long> {

    Boolean existsByEmail(String email);

    Account getByEmail(String email);

}

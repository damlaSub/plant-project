package co.simplon.plantproject.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.plantproject.entities.Account;

public interface AccountRepository
	extends JpaRepository<Account, Long> {

    Boolean existsByEmailIgnoreCase(String email);

    Optional<Account> findByEmailIgnoreCase(String email);

    Account findByEmail(String email);

}

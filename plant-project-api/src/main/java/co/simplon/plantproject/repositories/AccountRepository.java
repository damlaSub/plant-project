package co.simplon.plantproject.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.plantproject.dtos.AccountItem;
import co.simplon.plantproject.entities.Account;

public interface AccountRepository
	extends JpaRepository<Account, Long> {

    Boolean existsByEmail(String email);

    Account getByEmail(String email);

    AccountItem findProjectedById(Long id);

    Collection<AccountItem> findAllProjectedBy();

}

package co.simplon.plantproject.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.plantproject.dtos.AccountCreateDto;
import co.simplon.plantproject.dtos.AccountSigninDto;
import co.simplon.plantproject.dtos.TokenInfo;
import co.simplon.plantproject.entities.Account;
import co.simplon.plantproject.entities.Role;
import co.simplon.plantproject.repositories.AccountRepository;
import co.simplon.plantproject.repositories.RoleRepository;
import co.simplon.plantproject.utils.AuthHelper;

@Service
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private final AuthHelper authHelper;

    @Autowired(required = true)
    private final AccountRepository accountRepository;

    private final RoleRepository roleRepository;

    public AuthServiceImpl(AuthHelper authHelper,
	    AccountRepository accountRepository,
	    RoleRepository roleRepository) {
	this.authHelper = authHelper;
	this.accountRepository = accountRepository;
	this.roleRepository = roleRepository;

    }

    // Sign up, sign in implementation
    @Override
    @Transactional
    public void signUp(AccountCreateDto inputs) {

	// Check if account already exists in db
	if (!accountRepository
		.existsByEmail(inputs.getEmail())) {

	    Account account = new Account();
	    account.setFirstName(inputs.getFirstName());
	    account.setLastName(inputs.getLastName());
	    account.setEmail(inputs.getEmail());
	    String hashPassword = authHelper
		    .encode(inputs.getPassword());
	    account.setPassword(hashPassword);
	    Set<Role> roles = new HashSet<>();

	    // Assign "ROLE_ADMIN" if the account's email domain is "test.com".
	    if (account.getEmail().endsWith("@test.com")) {
		Role adminRole = roleRepository
			.findByCode("ROLE_ADMIN");
		roles.add(adminRole);
		account.setRole(adminRole);
	    } else {
		Role role = roleRepository
			.findByCode("ROLE_USER");
		roles.add(role);
		account.setRole(role);
	    }

	    accountRepository.save(account);
	} else {
	    throw new BadCredentialsException(
		    "This email is already associated with an account.");
	}
    }

    @Override
    public TokenInfo signIn(AccountSigninDto inputs) {
	String identifier = inputs.getEmail();
	String candidate = inputs.getPassword();

	Account account = accountRepository
		.getByEmail(identifier);
	System.out.println(account);

	if (account != null) {
	    boolean match = authHelper.matches(candidate,
		    account.getPassword());
	    if (match) {
		String name = account.getEmail();
		String token = authHelper.createJWT(null,
			name);
		TokenInfo tokenInfo = new TokenInfo();
		tokenInfo.setToken(token);
		return tokenInfo;
	    } else {
		throw new BadCredentialsException(
			"Wrong credentials");
	    }
	} else {
	    throw new BadCredentialsException(
		    "Wrong credentials");
	}
    }

    @Override
    public Boolean existsByEmail(String email) {

	Account account = accountRepository
		.getByEmail(email);

	return (account != null);
    }
}

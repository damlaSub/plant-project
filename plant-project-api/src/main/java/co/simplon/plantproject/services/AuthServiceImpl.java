package co.simplon.plantproject.services;

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

	Account account = new Account();
	account.setFirstName(inputs.getFirstName());
	account.setLastName(inputs.getLastName());
	account.setEmail(inputs.getEmail());
	String hashPassword = authHelper
		.encode(inputs.getPassword());
	account.setPassword(hashPassword);

	// Assign "ROLE_ADMIN" if the account's email domain is "test.com".
	if (account.getEmail().endsWith("@test.com")) {
	    Role adminRole = roleRepository
		    .getReferenceByCode("ROLE_ADMIN");
	    account.setRole(adminRole);
	} else {
	    Role role = roleRepository
		    .getReferenceByCode("ROLE_USER");
	    account.setRole(role);
	}
	System.out.println(account.getRole());
	accountRepository.save(account);

    }

    @Override
    @Transactional
    public TokenInfo signIn(AccountSigninDto inputs) {
	String identifier = inputs.getEmail();
	String candidate = inputs.getPassword();

	Account account = accountRepository
		.getByEmail(identifier);
	if (account != null) {
	    boolean match = authHelper.matches(candidate,
		    account.getPassword());
	    if (match) {
		String email = account.getEmail();
		String roleCode = account.getRole()
			.getCode();
		String token = authHelper
			.createJWT(roleCode, email);
		TokenInfo tokenInfo = new TokenInfo();
		tokenInfo.setToken(token);
		tokenInfo.setRole(roleCode);
		tokenInfo.setFirstName(
			account.getFirstName());
		return tokenInfo;

	    } else {
		throw new BadCredentialsException(
			"Invalid email or password.");
	    }
	} else if (account == null) {
	    throw new BadCredentialsException(
		    "Invalid email or password.");
	}
	return null;
    }

    @Override
    public Boolean existsByEmail(String email) {
	return this.accountRepository
		.existsByEmail(email.toString());

    }
}

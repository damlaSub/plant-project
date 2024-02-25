package co.simplon.plantproject.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.plantproject.dtos.AccountCreateDto;
import co.simplon.plantproject.dtos.AccountSigninDto;
import co.simplon.plantproject.dtos.RefreshTokenRequest;
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

	if (account.getEmail().endsWith("@plantme.com")) {
	    Role adminRole = roleRepository
		    .getReferenceByCode("ROLE_ADMIN");
	    account.setRole(adminRole);
	} else {
	    Role role = roleRepository
		    .getReferenceByCode("ROLE_USER");
	    account.setRole(role);
	}
	accountRepository.save(account);
    }

    @Override
    @Transactional
    public TokenInfo signIn(AccountSigninDto inputs) {
	String identifier = inputs.getEmail();
	String candidate = inputs.getPassword();

	Optional<Account> account = accountRepository
		.findByEmailIgnoreCase(identifier);
	if (account.isPresent()) {
	    boolean match = authHelper.matches(candidate,
		    account.get().getPassword());
	    if (match) {
		return createTokenFromAccount(
			account.get());
	    } else {
		throw new BadCredentialsException(
			"Invalid email or password");
	    }
	} else {
	    throw new BadCredentialsException(
		    "Invalid email or password");
	}
    }

    @Override
    public Boolean existsByEmail(String email) {
	return this.accountRepository
		.existsByEmailIgnoreCase(email.toString());

    }

    @Override
    public TokenInfo refreshToken(
	    RefreshTokenRequest request) {

	String idAsString = authHelper
		.getIdFromToken(request.getRefreshToken());
	Long id = Long.parseLong(idAsString);
	Optional<Account> account = accountRepository
		.findById(id);
	return createTokenFromAccount(account
		.orElseThrow(() -> new RuntimeException(
			"Token creation failed")));
    }

    @Override
    public TokenInfo createTokenFromAccount(
	    Account account) {
	String id = String.valueOf(account.getId());
	String roleCode = account.getRole().getCode();
	String token = authHelper.createJWT(roleCode, id);
	TokenInfo tokenInfo = new TokenInfo();
	tokenInfo.setToken(token);
	tokenInfo.setRole(roleCode);
	tokenInfo.setFirstName(account.getFirstName());
	String refreshToken = authHelper
		.createRefreshJWT(id);
	tokenInfo.setRefreshToken(refreshToken);
	return tokenInfo;
    }

}

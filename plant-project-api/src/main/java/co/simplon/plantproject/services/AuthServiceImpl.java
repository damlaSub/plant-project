package co.simplon.plantproject.services;

import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.plantproject.dtos.AccountCreateDto;
import co.simplon.plantproject.dtos.AccountSigninDto;
import co.simplon.plantproject.dtos.RefreshTokenRequest;
import co.simplon.plantproject.dtos.TokenInfo;
import co.simplon.plantproject.entities.Account;
import co.simplon.plantproject.errors.AccountNotFoundException;
import co.simplon.plantproject.repositories.AccountRepository;
import co.simplon.plantproject.utils.AuthHelper;

@Service
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private final AuthHelper authHelper;

    private final AccountRepository accountRepository;


    public AuthServiceImpl(AuthHelper authHelper,
	    AccountRepository accountRepository) {
	this.authHelper = authHelper;
	this.accountRepository = accountRepository;

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
	    account.setRole("ADMIN");
	} else {
	    account.setRole("USER");
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
    public TokenInfo refreshToken(
	    RefreshTokenRequest request) {
	String idAsString = authHelper
		.getIdFromToken(request.getRefreshToken());

	Long id = Long.parseLong(idAsString);
	Optional<Account> optAccount = Optional.of(
		accountRepository.findById(id).orElseThrow(
			(() -> new AccountNotFoundException(
				"Account not found"))));
	if (optAccount.isPresent()) {
	    Account account = new Account();
	    account.setEmail(optAccount.get().getEmail());
	    account.setFirstName(
		    optAccount.get().getFirstName());
	    account.setRole(optAccount.get().getRole());
	    return createTokenFromAccount(account);
	}
	return null;
    }

    TokenInfo createTokenFromAccount(Account account) {
	try {
	    String id = String.valueOf(account.getId());
	    String role = account.getRole();
	    String token = authHelper.createJWT(role,
		    id);
	    TokenInfo tokenInfo = new TokenInfo();
	    tokenInfo.setToken(token);
	    tokenInfo.setRole(role);
	    tokenInfo.setFirstName(account.getFirstName());
	    String refreshToken;
	    refreshToken = authHelper.createRefreshJWT(id);
	    tokenInfo.setRefreshToken(refreshToken);
	    return tokenInfo;
	} catch (Exception e) {
	    throw new RuntimeException(
		    "An error occurred during token creation",
		    e);
	}
    }

    @Override
    public Boolean existsByEmail(String email) {
	return this.accountRepository
		.existsByEmailIgnoreCase(email.toString());
    }
}

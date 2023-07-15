package co.simplon.plantproject.services;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.plantproject.dtos.Credentials;
import co.simplon.plantproject.dtos.TokenInfo;
import co.simplon.plantproject.entities.Account;
import co.simplon.plantproject.repositories.AuthRepository;
import co.simplon.plantproject.utils.AuthHelper;

@Service
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private final AuthHelper authHelper;
    private final AuthRepository authRepository;

    public AuthServiceImpl(AuthHelper authHelper,
	    AuthRepository authRepository) {
	this.authHelper = authHelper;
	this.authRepository = authRepository;

    }

    // Sign up, sign in implementation
    @Override
    @Transactional
    public void signUp(Credentials inputs) {
	Account account = new Account();
	account.setFirstName(inputs.getFirstName());
	account.setLastName(inputs.getLastName());
	account.setEmail(inputs.getEmail());

	String hashPassword = authHelper
		.encode(inputs.getPassword());
	account.setPassword(hashPassword);
	authRepository.save(account);

    }

    @Override
    public TokenInfo signIn(Credentials inputs) {
	String identifier = inputs.getEmail();
	String candidate = inputs.getPassword();

	Account account = authRepository
		.getByEmail(identifier);

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
}

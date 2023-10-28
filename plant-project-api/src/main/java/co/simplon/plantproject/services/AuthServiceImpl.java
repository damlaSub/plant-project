package co.simplon.plantproject.services;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.plantproject.dtos.SignUpCredentials;
import co.simplon.plantproject.dtos.SingInCredentials;
import co.simplon.plantproject.dtos.TokenInfo;
import co.simplon.plantproject.entities.User;
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
    public void signUp(SignUpCredentials inputs) {
	User user = new User();
	user.setFirstName(inputs.getFirstName());
	user.setLastName(inputs.getLastName());
	user.setEmail(inputs.getEmail());

	String hashPassword = authHelper
		.encode(inputs.getPassword());
	user.setPassword(hashPassword);
	authRepository.save(user);

    }

    @Override
    public TokenInfo signIn(SingInCredentials inputs) {
	String identifier = inputs.getEmail();
	String candidate = inputs.getPassword();

	User user = authRepository.getByEmail(identifier);

	if (user != null) {
	    boolean match = authHelper.matches(candidate,
		    user.getPassword());
	    if (match) {
		String name = user.getEmail();
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

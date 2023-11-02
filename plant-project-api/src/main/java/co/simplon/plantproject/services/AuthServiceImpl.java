package co.simplon.plantproject.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.plantproject.dtos.SignUpCredentials;
import co.simplon.plantproject.dtos.SingInCredentials;
import co.simplon.plantproject.dtos.TokenInfo;
import co.simplon.plantproject.entities.Role;
import co.simplon.plantproject.entities.User;
import co.simplon.plantproject.repositories.RoleRepository;
import co.simplon.plantproject.repositories.UserRepository;
import co.simplon.plantproject.utils.AuthHelper;

@Service
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private final AuthHelper authHelper;

    @Autowired(required = true)
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public AuthServiceImpl(AuthHelper authHelper,
	    UserRepository userRepository,
	    RoleRepository roleRepository) {
	this.authHelper = authHelper;
	this.userRepository = userRepository;
	this.roleRepository = roleRepository;

    }

    // Sign up, sign in implementation
    @Override
    @Transactional
    public void signUp(SignUpCredentials inputs) {
	if (!userRepository
		.existsByEmail(inputs.getEmail())) {

	    User user = new User();
	    user.setFirstName(inputs.getFirstName());
	    user.setLastName(inputs.getLastName());
	    user.setEmail(inputs.getEmail());

	    String hashPassword = authHelper
		    .encode(inputs.getPassword());
	    user.setPassword(hashPassword);
	    Set<Role> roles = new HashSet<>();

	    // Assign "ROLE_ADMIN" if the user's email domain is "cactus.co".
	    if (user.getEmail().endsWith("cactus.co")) {
		Role adminRole = roleRepository
			.findByName("ROLE_ADMIN");
		roles.add(adminRole);
		user.setRole(adminRole);
	    } else {
		Role userRole = roleRepository
			.findByName("ROLE_USER");
		roles.add(userRole);
		user.setRole(userRole);
	    }

	    userRepository.save(user);
	}
    }

    @Override
    public TokenInfo signIn(SingInCredentials inputs) {
	String identifier = inputs.getEmail();
	String candidate = inputs.getPassword();

	User user = userRepository.getByEmail(identifier);
	System.out.println(user);

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

    @Override
    public Boolean existsByEmail(String email) {

	User user = userRepository.getByEmail(email);

	return (user != null);
    }
}

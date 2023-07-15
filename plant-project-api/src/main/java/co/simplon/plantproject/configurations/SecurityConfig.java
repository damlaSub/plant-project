package co.simplon.plantproject.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.auth0.jwt.algorithms.Algorithm;

import co.simplon.plantproject.utils.AuthHelper;

@Configuration
public class SecurityConfig {

    @Value("${plant.auth.rounds}")
    private int rounds;
    @Value("${plant.auth.issuer}")
    private String issuer;
    @Value("${plant.auth.secret}")
    private String secret;
    @Value("${plant.auth.tokenExpiration}")
    private long tokenExpiration;

    @Bean
    public AuthHelper authHelper() {
	Algorithm algorithm = Algorithm.HMAC256(secret);
	PasswordEncoder encoder = new BCryptPasswordEncoder(
		rounds);

	return new AuthHelper.Builder().algorithm(algorithm)
		.passwordEncoder(encoder).issuer(issuer)
		.expiration(tokenExpiration).build();
    }

}

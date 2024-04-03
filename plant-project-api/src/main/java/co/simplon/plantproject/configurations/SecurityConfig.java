package co.simplon.plantproject.configurations;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import com.auth0.jwt.algorithms.Algorithm;

import co.simplon.plantproject.utils.AuthHelper;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${plant.auth.rounds}")
    private int rounds;
    @Value("${plant.auth.issuer}")
    private String issuer;
    @Value("${plant.auth.secret}")
    private String secret;
    @Value("${plant.auth.tokenExpiration}")
    private long tokenExpiration;
    @Value("${plant.auth.refreshTokenExpiration}")
    private long refreshTokenExpiration;

    @Bean
    AuthHelper authHelper() {
	Algorithm algorithm = Algorithm.HMAC256(secret);
	PasswordEncoder encoder = new BCryptPasswordEncoder(
		rounds);

	return new AuthHelper.Builder().algorithm(algorithm)
		.passwordEncoder(encoder).issuer(issuer)
		.expiration(tokenExpiration)
		.refreshTokenExpiration(
			refreshTokenExpiration)
		.build();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http)
	    throws Exception {
	http.cors(Customizer.withDefaults())
		.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests((authz) -> authz
			.requestMatchers(HttpMethod.POST,
				"/auth/sign-in",
				"/auth/sign-up",
				"/auth/refresh-token")
			.permitAll()
			.requestMatchers(HttpMethod.GET,
				"/sunlights", "/hydrations",
				"/plants")
			.permitAll()
			.requestMatchers("/my-plants/{id}",
				"/my-plants/add",
				"/my-plants/{id}/delete",
				"/my-plants/status")
			.hasAuthority("ROLE_USER")
			.requestMatchers(
				"/plants/admin/create",
				"/plants/admin/{id}",
				"/plants/admin/{id}/for-update",
				"/plants/admin/{id}/delete")
			.hasAuthority("ROLE_ADMIN")
			.anyRequest().authenticated())
		.oauth2ResourceServer((
			oauth2ResourceServer) -> oauth2ResourceServer
				.jwt(Customizer
					.withDefaults()));
	return http.build();
    }

    @Bean
    JwtAuthenticationConverter authenticationConverter() {
	JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
	authoritiesConverter
		.setAuthoritiesClaimName("role");
	authoritiesConverter.setAuthorityPrefix("ROLE_");
	JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
	authenticationConverter
		.setJwtGrantedAuthoritiesConverter(
			authoritiesConverter);
	return authenticationConverter;
    }

    @Bean
    JwtDecoder jwtDecoder() throws Exception {
	SecretKeySpec key = new SecretKeySpec(
		secret.getBytes(), "HmacSHA256");
	return NimbusJwtDecoder.withSecretKey(key).build();
    }
}

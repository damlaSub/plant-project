package co.simplon.plantproject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
	@Component
	class Tokens {

	    @Value("${plant.tests.fake-token}")
	    private String fake;

	    @Value("${plant.tests.bad-secret-token}")
	    private String badSecret;

	    @Value("${plant.tests.bad-issuer-token}")
	    private String badIssuer;

	    @Value("${plant.tests.expired-token}")
	    private String expired;

	    @Value("${plant.tests.valid-admin-token}")
	    private String admin;

	    @Value("${plant.tests.valid-user-token}")
	    private String user;

	    String get(final String name) {
	    switch (name) {
	        case "fake":
	        return fake;
	        case "badSecret":
	        return "Bearer " + badSecret;
	        case "badIssuer":
	        return "Bearer " + badIssuer;
	        case "expired":
	        return "Bearer " + expired;
	        case "admin":
	        return "Bearer " + admin;
	        case "user":
	        return "Bearer " + user;
	        default:
	        throw new IllegalArgumentException("Unexpected value: " + name);
	    }
	   
	   }
	 }

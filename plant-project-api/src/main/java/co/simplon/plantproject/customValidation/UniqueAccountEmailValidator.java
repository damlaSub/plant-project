package co.simplon.plantproject.customValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.security.authentication.BadCredentialsException;

import co.simplon.plantproject.services.AuthService;

public class UniqueAccountEmailValidator implements
	ConstraintValidator<UniqueAccountEmail, String> {

    final private AuthService service;

    public UniqueAccountEmailValidator(
	    AuthService service) {
	this.service = service;
    }

    @Override
    public boolean isValid(String email,
	    ConstraintValidatorContext context) {
	if ((email != null)
		&& this.service.existsByEmail(email)) {
	    throw new BadCredentialsException(
		    "This email is already associated with an account.");
	}
	return true;

    }
}
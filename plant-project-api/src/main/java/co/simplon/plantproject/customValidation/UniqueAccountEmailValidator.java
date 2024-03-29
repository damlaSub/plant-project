package co.simplon.plantproject.customValidation;

import java.util.Objects;

import co.simplon.plantproject.services.AuthService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

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
	if ((Objects.nonNull(email))
		&& this.service.existsByEmail(email)) {
	    return false;
	}
	return true;

    }
}
package co.simplon.plantproject.customValidation;

import co.simplon.plantproject.dtos.AccountCreateDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SamePasswordsValidator implements
	ConstraintValidator<SamePasswords, AccountCreateDto> {

    @Override
    public boolean isValid(AccountCreateDto value,
	    ConstraintValidatorContext context) {
	String password = value.getPassword();
	if (password == null) {
	    return false;
	}
	String confirmedPassword = value.getConfirm();
	return password.equals(confirmedPassword);
    }
}
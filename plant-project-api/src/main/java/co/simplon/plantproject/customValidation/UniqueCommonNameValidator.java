package co.simplon.plantproject.customValidation;

import java.util.Objects;

import co.simplon.plantproject.services.PlantService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueCommonNameValidator  implements
ConstraintValidator<UniqueCommonName, String>{
	final private PlantService service;

    public UniqueCommonNameValidator(
    		PlantService service) {
	this.service = service;
    }

    @Override
    public boolean isValid(String commonName,
	    ConstraintValidatorContext context) {
	if ((Objects.nonNull(commonName))
		&& this.service.existsByCommonNameIgnoreCase(commonName)) {
	    return false;
	}
	return true;

    }
}

package co.simplon.plantproject.customValidation;

import java.util.Objects;

import co.simplon.plantproject.services.PlantService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueLatinNameValidator  implements
ConstraintValidator<UniqueLatinName, String>{
	final private PlantService service;

    public UniqueLatinNameValidator(
    		PlantService service) {
	this.service = service;
    }

    @Override
    public boolean isValid(String latinName,
	    ConstraintValidatorContext context) {
	if ((Objects.nonNull(latinName))
		&& this.service.existsByLatinName(latinName)) {
	    return false;
	}
	return true;

    }

}

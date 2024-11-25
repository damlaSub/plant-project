package co.simplon.plantproject.customValidation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileTypeValidator implements
	ConstraintValidator<FileType, MultipartFile> {

    private static final List<String> VALID_FILE_TYPES = new ArrayList<String>();
    static {
	VALID_FILE_TYPES.add("image/gif");
	VALID_FILE_TYPES.add("image/png");
	VALID_FILE_TYPES.add("image/jpeg");
    }

    @Override
    public boolean isValid(MultipartFile value,
	    ConstraintValidatorContext context) {
	if (value == null) {
	    return true;
	}

	if (VALID_FILE_TYPES
		.contains(value.getContentType())) {
	    return true;

	}

	return false;
    }

}
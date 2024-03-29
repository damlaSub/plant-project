package co.simplon.plantproject.errors;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice

class ErrorExceptionHandler
	extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<ErrorMessage> handleBadCredentialsException(
	    BadCredentialsException ex) {
	ErrorMessage errorMessage = new ErrorMessage(
		HttpStatus.UNAUTHORIZED.value(),
		"Unauthorized", ex.getMessage());
	return new ResponseEntity<>(errorMessage,
		HttpStatus.UNAUTHORIZED);
    }

//    // @ExceptionHandler(HttpClientErrorException.Forbidden.class)
//    @ExceptionHandler(Forbidden.class)
//    protected ResponseEntity<ErrorMessage> handleForbiddenException(
//	    HttpClientErrorException.Forbidden ex) {
//	ErrorMessage errorMessage = new ErrorMessage(
//		 HttpStatus.FORBIDDEN.value(),
//		"Forbidden", ex.getMessage());
//	return new ResponseEntity<>(errorMessage,
//		HttpStatus.FORBIDDEN);
//    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<ErrorMessage> handleResourceNotFoundException(
	    ResourceNotFoundException ex) {
	ErrorMessage errorMessage = new ErrorMessage(
		HttpStatus.NOT_FOUND.value(), "Not found",
		ex.getMessage());
	return new ResponseEntity<>(errorMessage,
		HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorMessage> globalExceptionHandler(
	    Exception ex, WebRequest request) {
	ErrorMessage message = new ErrorMessage(
		HttpStatus.INTERNAL_SERVER_ERROR.value(),
		ex.getMessage(),
		request.getDescription(false));

	return new ResponseEntity<ErrorMessage>(message,
		HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<ErrorMessage> handleBadRequestException(
	    BadRequestException ex) {
	ErrorMessage errorMessage = new ErrorMessage(
		HttpStatus.BAD_REQUEST.value(),
		"Bad request", ex.getMessage());
	return new ResponseEntity<>(errorMessage,
		HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
	    MethodArgumentNotValidException ex,
	    HttpHeaders headers, HttpStatusCode status,
	    WebRequest request) {
	ValidationErrors errors = new ValidationErrors();
	List<FieldError> fieldErrors = ex.getFieldErrors();
	fieldErrors.forEach((e) -> errors
		.addFieldError(e.getField(), e.getCode()));
	List<ObjectError> globalErrors = ex
		.getGlobalErrors();
	globalErrors.forEach(
		(e) -> errors.addGlobalErrors(e.getCode()));

	return handleExceptionInternal(ex, errors, headers,
		status, request);
    }

}

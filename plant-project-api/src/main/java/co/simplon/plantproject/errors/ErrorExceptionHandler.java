package co.simplon.plantproject.errors;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.auth0.jwt.exceptions.JWTVerificationException;

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

    @ExceptionHandler(AccountNotFoundException.class)
    protected ResponseEntity<ErrorMessage> handleAccountNotFoundException(
	    AccountNotFoundException ex) {
	ErrorMessage errorMessage = new ErrorMessage(
		HttpStatus.UNAUTHORIZED.value(),
		"Unauthoried", ex.getMessage());
	return new ResponseEntity<>(errorMessage,
		HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Conflict.class)
    protected ResponseEntity<ErrorMessage> handleConflict(
	    Conflict ex) {
	ErrorMessage errorMessage = new ErrorMessage(
		HttpStatus.CONFLICT.value(), "Conflict",
		ex.getMessage());
	return new ResponseEntity<>(errorMessage,
		HttpStatus.CONFLICT);
    }

    @ExceptionHandler(JWTVerificationException.class)
    protected ResponseEntity<ErrorMessage> handleJWTVerificationException(
	    JWTVerificationException ex) {
	ErrorMessage errorMessage = new ErrorMessage(
		HttpStatus.FORBIDDEN.value(),
		"JWTVerificationException",
		ex.getMessage());
	return new ResponseEntity<>(errorMessage,
		HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationServiceException.class)
    protected ResponseEntity<ErrorMessage> handleAuthenticationServiceException(
	    AuthenticationServiceException ex) {
	ErrorMessage errorMessage = new ErrorMessage(
		HttpStatus.UNAUTHORIZED.value(),
		"AuthenticationServiceException",
		ex.getMessage());
	return new ResponseEntity<>(errorMessage,
		HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NumberFormatException.class)
    protected ResponseEntity<ErrorMessage> handleNumberFormatException(
	    NumberFormatException ex) {
	ErrorMessage errorMessage = new ErrorMessage(
		HttpStatus.UNAUTHORIZED.value(),
		"NumberFormatException", ex.getMessage());
	return new ResponseEntity<>(errorMessage,
		HttpStatus.UNAUTHORIZED);
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

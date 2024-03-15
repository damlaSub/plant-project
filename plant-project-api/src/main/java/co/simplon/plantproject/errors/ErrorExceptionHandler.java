package co.simplon.plantproject.errors;

import java.util.NoSuchElementException;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException.GatewayTimeout;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
//@ResponseBody
public class ErrorExceptionHandler
	extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<Object> handlBadRequestException(
	    BadRequestException ex) {
	String body = ex.getMessage();
	return handleExceptionInternal(ex, body,
		new HttpHeaders(), HttpStatus.BAD_REQUEST,
		null);
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<Object> handleBadCredentialException(
	    BadCredentialsException ex) {
	String body = ex.getMessage();
	return handleExceptionInternal(ex, body,
		new HttpHeaders(), HttpStatus.UNAUTHORIZED,
		null);
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    protected ResponseEntity<Object> handleForbiddenException(
	    HttpClientErrorException.Forbidden ex) {
	String body = ex.getMessage();
	return handleExceptionInternal(ex, body,
		new HttpHeaders(), HttpStatus.FORBIDDEN,
		null);
    }

//    @ExceptionHandler(ResourceNotFoundException.class)
//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
//      ErrorMessage message = new ErrorMessage(...);
//      return message;
//    }

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<Object> handleNoSuchElementException(
	    NoSuchElementException ex) {
	String body = ex.getMessage();
	return handleExceptionInternal(ex, body,
		new HttpHeaders(), HttpStatus.NOT_FOUND,
		null);
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleAccessDeniedException(
	    AccessDeniedException ex) {
	String body = ex.getMessage();
	return handleExceptionInternal(ex, body,
		new HttpHeaders(), HttpStatus.FORBIDDEN,
		null);
    }

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<Object> handleInternalServerError(
	    InternalServerError ex) {
	String body = ex.getMessage();
	return handleExceptionInternal(ex, body,
		new HttpHeaders(),
		HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    @ExceptionHandler(GatewayTimeout.class)
    public ResponseEntity<Object> handleGatewayTimeout(
	    GatewayTimeout ex) {
	String body = ex.getMessage();
	return handleExceptionInternal(ex, body,
		new HttpHeaders(),
		HttpStatus.GATEWAY_TIMEOUT, null);
    }

}

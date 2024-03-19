package co.simplon.plantproject.errors;

import java.util.Date;
import java.util.NoSuchElementException;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseBody
public class ErrorExceptionHandler
	extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorMessage> handleBadCredentialsException(
	    BadCredentialsException ex) {
	ErrorMessage errorMessage = new ErrorMessage(
		new Date(), HttpStatus.UNAUTHORIZED.value(),
		"Unauthorized", ex.getMessage());
	return new ResponseEntity<>(errorMessage,
		HttpStatus.UNAUTHORIZED);
    }

//    // @ExceptionHandler(HttpClientErrorException.Forbidden.class)
//    @ExceptionHandler(Forbidden.class)
//    public ResponseEntity<ErrorMessage> handleForbiddenException(
//	    HttpClientErrorException.Forbidden ex) {
//	ErrorMessage errorMessage = new ErrorMessage(
//		new Date(), HttpStatus.FORBIDDEN.value(),
//		"Forbidden", ex.getMessage());
//	return new ResponseEntity<>(errorMessage,
//		HttpStatus.FORBIDDEN);
//    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorMessage> handleNoSuchElementException(
	    NoSuchElementException ex) {
	ErrorMessage errorMessage = new ErrorMessage(
		new Date(), HttpStatus.NOT_FOUND.value(),
		"Not found", ex.getMessage());
	return new ResponseEntity<>(errorMessage,
		HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleResourceNotFoundException(
	    ResourceNotFoundException ex) {
	ErrorMessage errorMessage = new ErrorMessage(
		new Date(), HttpStatus.NOT_FOUND.value(),
		"Not found", ex.getMessage());
	return new ResponseEntity<>(errorMessage,
		HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(
	    Exception ex, WebRequest request) {
	ErrorMessage message = new ErrorMessage(new Date(),
		HttpStatus.INTERNAL_SERVER_ERROR.value(),
		ex.getMessage(),
		request.getDescription(false));

	return new ResponseEntity<ErrorMessage>(message,
		HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(
	    BadRequestException ex) {
	ErrorMessage errorMessage = new ErrorMessage(
		new Date(), HttpStatus.BAD_REQUEST.value(),
		"Bad request", ex.getMessage());
	return new ResponseEntity<>(errorMessage,
		HttpStatus.BAD_REQUEST);
    }

}

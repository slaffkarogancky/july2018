package golden.horde.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import golden.horde.exception.EntityNotFoundedException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntityNotFoundedException.class)
	protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundedException ex, final WebRequest request) {
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = "";
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
	        message += ("Кривое " + error.getField() + ": задали '" + error.getRejectedValue().toString() + "', но "+ error.getDefaultMessage());
	    }
		return handleExceptionInternal(ex, message, headers, HttpStatus.BAD_REQUEST, request);
	}
}
	
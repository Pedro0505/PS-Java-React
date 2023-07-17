package br.com.banco.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.banco.services.exception.BadRequestException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<StandardError> resourceNotFound(BadRequestException e, HttpServletRequest request) {
		String error = "Error in your request";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
}
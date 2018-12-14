package com.renatovirto.cursomc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.renatovirto.cursomc.exceptions.DataIntegrityViolation;
import com.renatovirto.cursomc.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException objectNotFound,
			HttpServletRequest httpRequest) {
		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(),
				objectNotFound.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegrityViolation.class)
	public ResponseEntity<StandardError> dataIntegrityViolation(DataIntegrityViolation dataIntegrity,
			HttpServletRequest httpRequest) {
		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(),
				dataIntegrity.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> methodArgument(MethodArgumentNotValidException methodArgument,
			HttpServletRequest httpRequest) {
		ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(),
				"Erro de validação!", System.currentTimeMillis());
		
		for (FieldError x : methodArgument.getBindingResult().getFieldErrors()) {
			error.addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}

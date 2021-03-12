package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.model.ErrorMessage;

@ControllerAdvice
public class DataNotFoundExceptionMapper {
	
	/** 
	 * 	Handling exception specifically
	 * 
	 */
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<?> handleDataNotFoundException(DataNotFoundException exception, WebRequest request) {
		
		ErrorMessage message = new ErrorMessage(exception.getMessage(), 404, request.getDescription(false));
		return new ResponseEntity(message, HttpStatus.NOT_FOUND);
	}
	/**
	 * 	Handling exception globally
	 *  
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest request) {
		
		ErrorMessage message = new ErrorMessage(exception.getMessage(), 500, request.getDescription(false));
		return new ResponseEntity(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

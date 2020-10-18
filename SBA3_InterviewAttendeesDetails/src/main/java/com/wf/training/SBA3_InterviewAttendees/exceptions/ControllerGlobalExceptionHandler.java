package com.wf.training.SBA3_InterviewAttendees.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerGlobalExceptionHandler {

	@ExceptionHandler(CustomExceptions.class)
	public ResponseEntity<CustomExceptions> fieldValidationHandler(CustomExceptions customExceptions) {
		return new ResponseEntity<CustomExceptions>(customExceptions, HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(UserAlreadyPresentException.class)
	public ResponseEntity<ExceptionResponse> handler(UserAlreadyPresentException ex) {
		ExceptionResponse exResponse = new ExceptionResponse(ex.getMessage() + "Global", System.currentTimeMillis(),
				HttpStatus.NOT_FOUND.value());
		ResponseEntity<ExceptionResponse> response = new ResponseEntity<ExceptionResponse>(exResponse,
				HttpStatus.NOT_FOUND);
		return response;
	}

}

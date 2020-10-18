package com.wf.training.SBA3_InterviewAttendees.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@ControllerAdvice
public class UserAlreadyAddedExcecption extends RuntimeException{
	
	 private LocalDateTime errorTimeStamp; 
	 private String errorCode; 
	 private String errorMessage;	 
	 	
	
}

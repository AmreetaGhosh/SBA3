package com.wf.training.SBA3_InterviewAttendees.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class UserAlreadyPresentException extends RuntimeException {
	
	public UserAlreadyPresentException(String message)
	{
		super(message);
	}

}

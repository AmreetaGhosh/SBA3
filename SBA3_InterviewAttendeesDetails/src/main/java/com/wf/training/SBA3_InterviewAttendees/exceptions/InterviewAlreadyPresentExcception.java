package com.wf.training.SBA3_InterviewAttendees.exceptions;

import java.time.LocalDateTime;

public class InterviewAlreadyPresentExcception extends RuntimeException{
	private LocalDateTime errorTimeStamp;
	private String errorCode;
	private String errorMessage;
	
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}

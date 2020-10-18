package com.wf.training.SBA3_InterviewAttendees.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

	private String message;
	private Long timeStamp;
	private int status;
}

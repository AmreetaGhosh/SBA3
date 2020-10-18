package com.wf.training.SBA3_InterviewAttendees.service;

import java.util.List;

import javax.validation.Valid;

import com.wf.training.SBA3_InterviewAttendees.dto.InterviewDetailsDto;



public interface InterviewAttendeeService {
	
	public List<InterviewDetailsDto> getAllInterviews();
	
	public InterviewDetailsDto addAttendees(String interviewName,Integer userId);
	public InterviewDetailsDto getAttendees(String intName);
	
	public InterviewDetailsDto saveInterview(InterviewDetailsDto interviewDto);
	public InterviewDetailsDto getInterview(String technology);
	public InterviewDetailsDto deleteInterview(String technology);
	
	public InterviewDetailsDto updateStatus(String interviewName, String status);
	
	public boolean isAttendeeAddedToInterview(String interviewName,Integer userId);
	public List<InterviewDetailsDto> getInterviewByInterviewor(String interviewerName);
	
	public boolean isExists(String fname, String lname);
	public boolean isInterviewPresent(String interviewName);
	
}

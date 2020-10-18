package com.wf.training.SBA3_InterviewAttendees.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.wf.training.SBA3_InterviewAttendees.entity.Interview;



public class InterviewConvertor {
	
	public static Interview interviewDtoToInterviewConvertor(InterviewDetailsDto interviewDetailsDto) {
		
		Interview interview=new Interview();
		
		interview.setDate(LocalDate.now());
		interview.setTime(LocalTime.now());
		interview.setInterviewerName(interviewDetailsDto.getInterviewerName());
		interview.setInterviewName(interviewDetailsDto.getInterviewName());
		interview.setInterviewStatus(interviewDetailsDto.getInterviewStatus());
		interview.setRemarks(interviewDetailsDto.getRemarks());
		
		for(String skill:interviewDetailsDto.getUserSkills()) {
			
			if(interview.getUserSkills()==null)
				interview.setUserSkills(skill);
			else
				interview.setUserSkills(interview.getUserSkills()+","+skill);
				
		}
		
		
		return interview;
	}
	
	public static InterviewDetailsDto interviewToInterviewDtoConvertor(Interview interview)
	{
		InterviewDetailsDto interviewDetailsDto=new InterviewDetailsDto();
		String[] skillset=interview.getUserSkills().split(",");
		
		interviewDetailsDto.setInterviewerName(interview.getInterviewerName());
		interviewDetailsDto.setInterviewId(interview.getInterviewId());
		interviewDetailsDto.setInterviewName(interview.getInterviewName());
		interviewDetailsDto.setInterviewStatus(interview.getInterviewStatus());
		interviewDetailsDto.setRemarks(interview.getRemarks());
		interviewDetailsDto.setTime(interview.getTime());
		interviewDetailsDto.setDate(interview.getDate());
		
		
		interviewDetailsDto.setUserSkills(Arrays.asList(skillset));
		interviewDetailsDto.setUsers(interview.getUsers());
		
		
		return interviewDetailsDto;
	}

}

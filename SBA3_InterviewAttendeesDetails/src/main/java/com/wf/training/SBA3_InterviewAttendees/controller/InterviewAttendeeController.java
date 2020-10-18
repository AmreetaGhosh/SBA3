package com.wf.training.SBA3_InterviewAttendees.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wf.training.SBA3_InterviewAttendees.dto.InterviewDetailsDto;
import com.wf.training.SBA3_InterviewAttendees.exceptions.CustomExceptions;
import com.wf.training.SBA3_InterviewAttendees.service.InterviewAttendeeService;
import com.wf.training.SBA3_InterviewAttendees.service.UserService;

@RestController
public class InterviewAttendeeController {
	
	@Autowired
	UserService userService;
	@Autowired
	InterviewAttendeeService interviewService;	

	
	
	@PostMapping("/interviews")
	public ResponseEntity<InterviewDetailsDto> addInterview(@Valid @RequestBody InterviewDetailsDto interviewDetailsDto,BindingResult resultDetails) {
		
	
		if(resultDetails.hasErrors())
		{
			CustomExceptions exception=new CustomExceptions();
			for(FieldError err:resultDetails.getFieldErrors())
			{
				if(exception.getErrorMessage()==null) {
				exception.setErrorCode(err.getCode());
				exception.setErrorMessage(err.getDefaultMessage());
				}
				else
				{
					exception.setErrorCode(exception.getErrorCode()+" || "+err.getCode());
					exception.setErrorMessage(exception.getErrorMessage()+" || "+err.getDefaultMessage());					
				}
			}
			exception.setErrorTimeStamp(LocalDateTime.now());
			throw exception;
		}
		
		if(interviewDetailsDto.getUserSkills() == null || interviewDetailsDto.getUserSkills().size() == 0 || interviewDetailsDto.getUserSkills().contains(null) || interviewDetailsDto.getUserSkills().contains(""))
		{
			throw new CustomExceptions(LocalDateTime.now(),"Interview Skills Error","Interview Skills should'nt be null or empty or blank");
		}
		return new ResponseEntity<InterviewDetailsDto>(interviewService.saveInterview(interviewDetailsDto),HttpStatus.OK);
	}
		
	
	@GetMapping("/getAttendeesByInterviewName/{name}")
	public ResponseEntity<InterviewDetailsDto> getAttendees(@PathVariable("name") String intName)
	{
		if(interviewService.getInterview(intName)==null)
			throw new CustomExceptions(LocalDateTime.now(),"Interview Exception","Interview doesnt exist");
		
		return new ResponseEntity<InterviewDetailsDto>(interviewService.getAttendees(intName),HttpStatus.OK);
	}
	
	@GetMapping("/searchInterviewByInterviewName/{interviewName}")
	public ResponseEntity<InterviewDetailsDto> searchInterviewByName(@PathVariable("interviewName") String interviewName) {
		InterviewDetailsDto interview=interviewService.getInterview(interviewName);
		if(interview==null)
			throw new CustomExceptions(LocalDateTime.now(),"Interview Exception","Interview doesnt exist");
		
		return new ResponseEntity<InterviewDetailsDto>(interview,HttpStatus.OK);
	}
	
	@GetMapping("/searchInterviewByInterviewerName/{interviewerName}")
	public ResponseEntity<List<InterviewDetailsDto>> searchInterview(@PathVariable("interviewerName") String interviewerName) {
		List<InterviewDetailsDto> interviews=interviewService.getInterviewByInterviewor(interviewerName);
		if(interviews==null)
			throw new CustomExceptions(LocalDateTime.now(),"Interview Exception","Interview doesnt exist");
		
		return new ResponseEntity<List<InterviewDetailsDto>>(interviews,HttpStatus.OK);
	}
	
	
	@GetMapping("/interviews")
	public ResponseEntity<List<InterviewDetailsDto>> searchAllInterviews()
	{
		return new ResponseEntity<List<InterviewDetailsDto>>(interviewService.getAllInterviews(),HttpStatus.OK);
	}
	
	@PutMapping("/modifyStatus/{interviewName}/{status}")
	public ResponseEntity<InterviewDetailsDto> modifyInterviewStatus(@PathVariable("interviewName") String interviewName,@PathVariable("status") String status)
	{
		InterviewDetailsDto interview=interviewService.getInterview(interviewName);
		if(interview==null)
			throw new CustomExceptions(LocalDateTime.now(),"Interview Exception","Interview doesnt exist");
		return new ResponseEntity<InterviewDetailsDto>(interviewService.updateStatus(interviewName,status),HttpStatus.OK);
	}
	
		
	
	@GetMapping("/addAttendees/{interviewName}/{userId}")
	public ResponseEntity<InterviewDetailsDto> addAttendees(@PathVariable("interviewName") String interviewName,@PathVariable("userId") Integer userId)
	{

		if(interviewService.getInterview(interviewName)==null || userService.getUserById(userId)==null)
			throw new CustomExceptions(LocalDateTime.now(),"Association Exception","Inerview or user doesnt exist");
		
		if(interviewService.isAttendeeAddedToInterview(interviewName,userId))
			throw new CustomExceptions(LocalDateTime.now(),"Users Exception","One or more of the user Ids in the request are already added to the "+interviewName+" Interview");
		return new ResponseEntity<InterviewDetailsDto>(interviewService.addAttendees(interviewName,userId),HttpStatus.OK);
	}
	
	@DeleteMapping("/removeInterview/{interviewName}")
	public ResponseEntity<InterviewDetailsDto> removeInterview(@PathVariable("interviewName") String interviewName) {
		
		if(searchInterview(interviewName)==null)
			if(!interviewService.isInterviewPresent(interviewName))
			throw new CustomExceptions(LocalDateTime.now(),"Interview Exception", interviewName+" Interview already Deleted Or does'nt exist");
		return new ResponseEntity<InterviewDetailsDto>(interviewService.deleteInterview(interviewName),HttpStatus.OK);
	}
	
}

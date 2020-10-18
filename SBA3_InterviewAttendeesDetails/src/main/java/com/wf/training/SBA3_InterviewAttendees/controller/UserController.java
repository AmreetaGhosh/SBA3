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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wf.training.SBA3_InterviewAttendees.dto.UserDetailsDto;
import com.wf.training.SBA3_InterviewAttendees.exceptions.CustomExceptions;
import com.wf.training.SBA3_InterviewAttendees.service.InterviewAttendeeService;
import com.wf.training.SBA3_InterviewAttendees.service.UserService;
import com.wf.training.SBA3_InterviewAttendees.service.UserServiceImpl;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	@Autowired
	InterviewAttendeeService interviewService;
		
	
	@PostMapping("/users")
	public ResponseEntity<UserDetailsDto> addUser(@Valid @RequestBody UserDetailsDto userDetailsDto,BindingResult resultDetails) {
		
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
		
		return new ResponseEntity<UserDetailsDto>(userService.addUserService(userDetailsDto),HttpStatus.OK);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDetailsDto>> getAllUsers(){ 
		return new ResponseEntity<List<UserDetailsDto>>(userService.getAllUsersService(),HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{mobile}")
	public ResponseEntity<UserDetailsDto> deleteUser(@PathVariable("mobile") String mobile) {
		if(!userService.isUserPresent(mobile))
			throw new CustomExceptions(LocalDateTime.now(),"Users Exception", "User with Mobile Number "+mobile+"  does'nt exist");
		return new ResponseEntity<UserDetailsDto>(userService.deleteUserService(mobile),HttpStatus.OK);
	}
	
	
	


}

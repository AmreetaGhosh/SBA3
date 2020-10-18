package com.wf.training.SBA3_InterviewAttendees.service;

import java.util.List;
import java.util.Optional;

import com.wf.training.SBA3_InterviewAttendees.dto.UserDetailsDto;



public interface UserService {
	
	public List<UserDetailsDto> getAllUsersService();
	public UserDetailsDto addUserService(UserDetailsDto userDetailsDto);
	public UserDetailsDto deleteUserService(String mobile);
	
	public boolean isUserPresent(String mobile);
	public UserDetailsDto getUserById(Integer userId);	
	

}

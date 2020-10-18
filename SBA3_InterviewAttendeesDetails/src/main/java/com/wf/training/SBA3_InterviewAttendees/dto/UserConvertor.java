package com.wf.training.SBA3_InterviewAttendees.dto;

import com.wf.training.SBA3_InterviewAttendees.entity.User;

public class UserConvertor {
	
	public static User userDtoToUserConverted(UserDetailsDto userDetailsDto)
	{
		User user=new User();
		user.setEmail(userDetailsDto.getEmail());
		user.setFirstName(userDetailsDto.getFirstName());
		user.setLastName(userDetailsDto.getLastName());
		user.setMobile(userDetailsDto.getMobile());
		return user;
	}
	
	public static UserDetailsDto userToUserDtoConverter(User user)
	{
		UserDetailsDto userDetailsDto=new UserDetailsDto();
		userDetailsDto.setUserId(user.getUserid());
		userDetailsDto.setEmail(user.getEmail());
		userDetailsDto.setFirstName(user.getFirstName());
		userDetailsDto.setLastName(user.getLastName());
		userDetailsDto.setMobile(user.getMobile());
		
		return userDetailsDto;
	}

}

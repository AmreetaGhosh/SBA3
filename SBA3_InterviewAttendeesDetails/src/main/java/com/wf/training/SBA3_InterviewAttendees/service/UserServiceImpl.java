package com.wf.training.SBA3_InterviewAttendees.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wf.training.SBA3_InterviewAttendees.dto.UserConvertor;
import com.wf.training.SBA3_InterviewAttendees.dto.UserDetailsDto;
import com.wf.training.SBA3_InterviewAttendees.entity.User;
import com.wf.training.SBA3_InterviewAttendees.exceptions.UserAlreadyPresentException;
import com.wf.training.SBA3_InterviewAttendees.repo.InterviewRepo;
import com.wf.training.SBA3_InterviewAttendees.repo.UserRepo;



@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepo userrepository;
	
	@Autowired
	InterviewRepo interviewrepository;
	
	@Override
	public List<UserDetailsDto> getAllUsersService() {
		
		List<UserDetailsDto> list=new ArrayList<UserDetailsDto>();
		for(User user:userrepository.findAll())
			list.add(UserConvertor.userToUserDtoConverter(user));
			
		return list;
	}
	
	@Override
	public UserDetailsDto addUserService(UserDetailsDto userDetailsDto) {
		Optional<User> existingUser =  userrepository.findUserByFirstNameandLastName(userDetailsDto.getFirstName(), userDetailsDto.getLastName());
		if(!existingUser.isPresent())
		{
		User user=UserConvertor.userDtoToUserConverted(userDetailsDto);
		return UserConvertor.userToUserDtoConverter(userrepository.save(user));
		}else {
			throw new UserAlreadyPresentException("Record with given 'FirstName' and 'LastName' already exists"); 
		}
	}

	@Override
	public UserDetailsDto deleteUserService(String mobile) {
		User user=userrepository.findByMobile(mobile);
		if(user==null)
			return null;
		user.getInterviews().forEach(u->u.getUsers().remove(user));
		interviewrepository.saveAll(user.getInterviews());
		userrepository.deleteByMobile(mobile);
		return UserConvertor.userToUserDtoConverter(user);
	}

	

	@Override
	public boolean isUserPresent(String mobile) {
		if(userrepository.findByMobile(mobile)==null)
			return false;
		return true;
	}

	@Override
	public UserDetailsDto getUserById(Integer userId) {
		User user=userrepository.findById(userId).orElse(null);
		if(user==null)
			return null;
		return UserConvertor.userToUserDtoConverter(user);
	}



}

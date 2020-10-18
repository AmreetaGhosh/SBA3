package com.wf.training.SBA3_InterviewAttendees.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wf.training.SBA3_InterviewAttendees.dto.InterviewConvertor;
import com.wf.training.SBA3_InterviewAttendees.dto.InterviewDetailsDto;
import com.wf.training.SBA3_InterviewAttendees.entity.Interview;
import com.wf.training.SBA3_InterviewAttendees.entity.User;
import com.wf.training.SBA3_InterviewAttendees.repo.InterviewRepo;
import com.wf.training.SBA3_InterviewAttendees.repo.UserRepo;

@Service
@Transactional
public class InterviewAttendeeServiceImpl implements InterviewAttendeeService{

	@Autowired
	public InterviewRepo interviewRepo;
	@Autowired
	public UserRepo userRepo;
	
	@Override
	public InterviewDetailsDto saveInterview(InterviewDetailsDto interviewDetailsDto) {
		Interview interview=InterviewConvertor.interviewDtoToInterviewConvertor(interviewDetailsDto);
		interview.setDate(LocalDate.now());
		interview.setTime(LocalTime.now());
		interviewRepo.save(interview); 
		return InterviewConvertor.interviewToInterviewDtoConvertor(interview);
		
	}

	@Override
	public InterviewDetailsDto addAttendees(String interviewName,Integer userId) {
		User user;		
		Interview interview=interviewRepo.findByInterviewName(interviewName);
		List<User> interviewusers=interview.getUsers();
		if(interviewusers==null)
			interviewusers=new ArrayList<User>();
			user=userRepo.findById(userId).orElse(null);
			interviewusers.add(user);
		interview.setUsers(interviewusers);
		interviewRepo.save(interview);
		return InterviewConvertor.interviewToInterviewDtoConvertor(interview);
	}

	@Override
	public InterviewDetailsDto getAttendees(String intName) {
		return InterviewConvertor.interviewToInterviewDtoConvertor(interviewRepo.findByInterviewName(intName));
	}

	@Override
	public InterviewDetailsDto getInterview(String technology) {
		if(interviewRepo.findByInterviewName(technology)==null)
			return null;
		return InterviewConvertor.interviewToInterviewDtoConvertor(interviewRepo.findByInterviewName(technology));
	}
	@Override
	public List<InterviewDetailsDto> getInterviewByInterviewor(String name) {
		List<InterviewDetailsDto> list= new ArrayList<InterviewDetailsDto>();
		if(interviewRepo.findAllByInterviewerName(name)==null)
			return null;
		for(Interview interview:interviewRepo.findAllByInterviewerName(name))
		{
			list.add(InterviewConvertor.interviewToInterviewDtoConvertor(interview));
		}
		return list;
	}
	
	@Override
	public InterviewDetailsDto deleteInterview(String technology) {
		InterviewDetailsDto interviewDto=getInterview(technology);
		interviewRepo.deleteByInterviewName(technology);
		return interviewDto;
	}

	@Override
	public List<InterviewDetailsDto> getAllInterviews() {
		List<InterviewDetailsDto> intList=new ArrayList<InterviewDetailsDto>();
		for(Interview item:interviewRepo.findAll()) {
			intList.add(InterviewConvertor.interviewToInterviewDtoConvertor(item));
		}
		return intList;
	}

	@Override
	public InterviewDetailsDto updateStatus(String interviewName, String status) {
		Interview interview=interviewRepo.findByInterviewName(interviewName);
		interview.setInterviewStatus(status);
		return InterviewConvertor.interviewToInterviewDtoConvertor(interview);
	}


	@Override
	public boolean isAttendeeAddedToInterview(String interviewName,Integer userId) {
		Interview interview=interviewRepo.findByInterviewName(interviewName);
		boolean found=false;
		if(interview!=null)
		{
			List<User> users=interview.getUsers();
			for(User user:users) {
					if(userId==user.getUserid())
						found=true;				
			}
			
		}
		return found;
	}

	@Override
	public boolean isExists(String fname, String lname) {
		return false;
	}

	@Override
	public boolean isInterviewPresent(String interviewName) {
		return false;
	}

}

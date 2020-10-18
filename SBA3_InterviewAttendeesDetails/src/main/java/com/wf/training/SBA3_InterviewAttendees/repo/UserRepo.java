package com.wf.training.SBA3_InterviewAttendees.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wf.training.SBA3_InterviewAttendees.entity.User;



@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

	public Integer deleteByMobile(String mobile);
	public User findByMobile(String mobile);
	
	@Query(value = "select * from user_table at where first_name=?1 and last_name=?2",nativeQuery = true)
	Optional<User> findUserByFirstNameandLastName(String firstName,String lastName);
}

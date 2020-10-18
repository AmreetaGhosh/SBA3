package com.wf.training.SBA3_InterviewAttendees.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewAttendees {
	@NotBlank(message = "Interview Name can't be blank.")
	@Length(min = 3,max = 30,message = "Interview Name must be minimum of 3 and maximum of 30 characters. Please add details accordingly")
	private String interviewName;
	private List<Integer> userIds;
}

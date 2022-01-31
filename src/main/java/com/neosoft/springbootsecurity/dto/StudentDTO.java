package com.neosoft.springbootsecurity.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
	
private Integer studentId;
	
	private String firstName;
	private String lastName;
	private Integer phoneNumber;
	private String emailId;
	private List<ProjectDTO> projectDTOs;
	

}

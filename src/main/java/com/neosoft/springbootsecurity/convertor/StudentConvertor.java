package com.neosoft.springbootsecurity.convertor;

import org.springframework.stereotype.Component;

import com.neosoft.springbootsecurity.dto.StudentDTO;
import com.neosoft.springbootsecurity.entity.Student;

@Component
public class StudentConvertor {
	
	public Student dtoToEntity(StudentDTO studentDTO)
	{
		return Student.builder().firstName(studentDTO.getFirstName()).lastName(studentDTO.getLastName()).phoneNumber(studentDTO.getPhoneNumber()).
				emailId(studentDTO.getEmailId()).build();
		
	}
	public StudentDTO entityToDTO(Student student)
	{
		return StudentDTO.builder().firstName(student.getFirstName()).lastName(student.getLastName()).phoneNumber(student.getPhoneNumber()).
				emailId(student.getEmailId()).build();
		
	}

}

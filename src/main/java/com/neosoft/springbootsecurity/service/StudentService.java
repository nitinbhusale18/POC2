package com.neosoft.springbootsecurity.service;

import org.springframework.stereotype.Service;

import com.neosoft.springbootsecurity.entity.Student;

@Service
public interface StudentService {
	
	public Student save(Student student);
	
	public Student getStudentById(Integer id);

}

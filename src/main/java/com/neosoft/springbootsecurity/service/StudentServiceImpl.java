package com.neosoft.springbootsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.springbootsecurity.entity.Student;
import com.neosoft.springbootsecurity.repository.StudentRepository;

@Service("studentService")
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentRepository studentRepository;
	
	@Override
	public Student save(Student student) {
		
		return studentRepository.save(student);
	}

	@Override
	public Student getStudentById(Integer id) {
		
		return studentRepository.getById(id);
	}
	

}

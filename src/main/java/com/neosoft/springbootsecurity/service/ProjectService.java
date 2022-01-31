package com.neosoft.springbootsecurity.service;

import org.springframework.stereotype.Service;

import com.neosoft.springbootsecurity.entity.Project;

@Service
public interface ProjectService {
	
	 Project save(Project project);
	
	 Project getProjectById(Integer Id);

}

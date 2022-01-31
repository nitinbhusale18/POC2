package com.neosoft.springbootsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.springbootsecurity.entity.Project;
import com.neosoft.springbootsecurity.repository.ProjectRepository;

@Service("prjectService")
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	ProjectRepository projectRepository;
	
	@Override
	public Project save(Project project) {
		
		return projectRepository.save(project);
	}

	@Override
	public Project getProjectById(Integer Id) {
		
		return projectRepository.getById(Id);
	}
	

}

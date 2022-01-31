package com.neosoft.springbootsecurity.service;

import org.springframework.stereotype.Service;

import com.neosoft.springbootsecurity.entity.User;

@Service
public interface UserService {
	
	public User getUserByName(String name);
	
	
	

}

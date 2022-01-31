package com.neosoft.springbootsecurity.service;


import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.neosoft.springbootsecurity.config.Encoder;
import com.neosoft.springbootsecurity.entity.User;
import com.neosoft.springbootsecurity.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService,UserDetailsService{
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Encoder encoder;

	

	@Override
	public User getUserByName(String username) throws UsernameNotFoundException {

		User user = userRepository.findByName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return user;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		 User user = userRepository.findByName(username);

	        if (user == null) {
	            throw new UsernameNotFoundException("User not found !!");
	        } else {
	        	Collection<SimpleGrantedAuthority> authority=new ArrayList<>();
	        	user.getRoles().forEach(role -> {
	        		authority.add(new SimpleGrantedAuthority(role.getRoleName()));
	        	});
	            return new org.springframework.security.core.userdetails.User(user.getName(), encoder.passwordEncoder().encode(user.getPassword()), authority);
	        }
	}

	

}

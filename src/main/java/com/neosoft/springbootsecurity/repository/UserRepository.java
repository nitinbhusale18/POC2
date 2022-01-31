package com.neosoft.springbootsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neosoft.springbootsecurity.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByName(String name);

}

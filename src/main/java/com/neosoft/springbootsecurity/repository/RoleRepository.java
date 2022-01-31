package com.neosoft.springbootsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neosoft.springbootsecurity.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}

package com.neosoft.springbootsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neosoft.springbootsecurity.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{

}

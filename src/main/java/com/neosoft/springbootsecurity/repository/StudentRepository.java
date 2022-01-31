package com.neosoft.springbootsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neosoft.springbootsecurity.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

}

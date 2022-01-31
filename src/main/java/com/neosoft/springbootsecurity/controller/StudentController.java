package com.neosoft.springbootsecurity.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.neosoft.springbootsecurity.convertor.StudentConvertor;
import com.neosoft.springbootsecurity.dto.ProjectDTO;
import com.neosoft.springbootsecurity.dto.StudentDTO;
import com.neosoft.springbootsecurity.entity.Project;
import com.neosoft.springbootsecurity.entity.Student;
import com.neosoft.springbootsecurity.repository.ProjectRepository;
import com.neosoft.springbootsecurity.repository.StudentRepository;
import com.neosoft.springbootsecurity.service.StudentService;

import io.jsonwebtoken.lang.Arrays;

@RestController
public class StudentController {

	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	StudentService studentService;

	@Autowired
	Gson gson;

	@Autowired
	StudentConvertor studentConvertor;

	@Autowired
	ProjectRepository projectRepository;

	/* @PreAuthorize(value = "hasAuthority('ADMIN')") */
	@GetMapping(value = "/rest/admin/student")
	public ResponseEntity<List> getAllStudent() {
		logger.info("--All user list--");
		List<Student> userList = null;
		try {
			userList = studentRepository.findAll();
			List<StudentDTO> response = userList.stream().map(this::convertToResponse).collect(Collectors.toList());

			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(java.util.Arrays.asList("Some thing wrong"), HttpStatus.BAD_REQUEST);
		}

	}

	StudentDTO convertToResponse(Student student) {
		StudentDTO dto = new StudentDTO();
		dto.setEmailId(student.getEmailId());
		dto.setFirstName(student.getFirstName());
		dto.setLastName(student.getLastName());
		dto.setPhoneNumber(student.getPhoneNumber());
		dto.setStudentId(student.getStudentId());
		List<ProjectDTO> list = new ArrayList<>();
		for (Project pro : student.getProjects()) {
			ProjectDTO proDTO = new ProjectDTO();
			proDTO.setDuration(pro.getDuration());
			proDTO.setProjname(pro.getProjname());
			proDTO.setProjid(pro.getProjid());
			list.add(proDTO);
		}
		dto.setProjectDTOs(list);
		return dto;

	}

	/* @PreAuthorize(value = "hasAuthority('USER')") */
	@PostMapping(value = "/rest/user/student")
	public ResponseEntity<String> addStudent(@Valid @RequestBody StudentDTO studentDTO, HttpServletRequest request,
			HttpServletResponse response) {

		Student student = null;
		logger.info("-- post mapping call--");
		try {
			student = studentConvertor.dtoToEntity(studentDTO);
			student = studentService.save(student);
			for (ProjectDTO pDTO : studentDTO.getProjectDTOs()) {
				Project project = new Project();
				project.setProjname(pDTO.getProjname());
				project.setDuration(pDTO.getDuration());
				project.setStudent(student);
				projectRepository.save(project);
			}

			response.setStatus(HttpStatus.CREATED.value());
			logger.info("-- post mapping call successfully--");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("-- Something went wrong --");
			return new ResponseEntity<>(gson.toJson("Something went wrong"), HttpStatus.BAD_REQUEST);

		}

		return new ResponseEntity<>(gson.toJson("Data added success fully"), HttpStatus.CREATED);

	}

	/* @PreAuthorize(value = "hasAuthority('ADMIN')") */
	@PatchMapping(value = "/rest/admin/student/{id}")
	public ResponseEntity<String> findByStudentId(@PathVariable Integer id, HttpServletRequest request,
			HttpServletResponse response) {
		StudentDTO studentDTO = null;
		logger.info("-- PATCH mapping call--");
		try {

			Student student = studentService.getStudentById(id);
			if (student != null) {
				studentDTO = studentConvertor.entityToDTO(student);
				response.setStatus(HttpStatus.OK.value());
				logger.info("-- PATCH mapping call successfully--");
				return new ResponseEntity<>(gson.toJson(studentDTO), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(gson.toJson("User Not Available"), HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> map = new HashMap<>();
			map.put("status", "Bad Request");
			map.put("message", "Record not found for this id");
			return new ResponseEntity<>(gson.toJson(map), HttpStatus.BAD_REQUEST);
		}

	}

}

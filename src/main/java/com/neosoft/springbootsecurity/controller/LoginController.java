package com.neosoft.springbootsecurity.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.neosoft.springbootsecurity.config.Encoder;
import com.neosoft.springbootsecurity.dto.UserDTO;
import com.neosoft.springbootsecurity.entity.User;
import com.neosoft.springbootsecurity.repository.UserRepository;

@RestController
public class LoginController {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private Encoder encoder;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Gson gson;

	

	
	@GetMapping("/login")
	public ModelAndView loginUser(@RequestParam(value = "error", required = false) final String error,
			@RequestParam(value = "logout", required = false) final String logout,
			@RequestParam(value = "success", required = false) final String success,
			final HttpServletRequest request, Model model)
	{
		
		if (error != null) {
            model.addAttribute("error", "Some thing is wrong");
        }else if (logout != null) {
            model.addAttribute("msg", "You are now logged out !");
            request.getSession().invalidate();
        }else if (success != null) {
            model.addAttribute("msg", "User Created Successfully !");
        }
		ModelAndView modelAndView=new ModelAndView();
		
		modelAndView.setViewName("login");
		return modelAndView;
		
	}
	@PostMapping("/registeruser")
	public void saveuser(@RequestBody User u)
	{
		User user=User.builder()
				.name(u.getName())
				.password(passwordEncoder.encode(u.getPassword()))
				.roles(u.getRoles())
				.build();
				userRepository.save(user);
	}
	
	@GetMapping("/success")
	public ModelAndView success()
	{
		ModelAndView modelAndView=new ModelAndView();
		
		modelAndView.setViewName("success");
		return modelAndView;
	}
	
	@GetMapping("/ragister")
	public ModelAndView ragister()
	{
		ModelAndView modelAndView=new ModelAndView();
		
		modelAndView.setViewName("ragister");
		return modelAndView;
	}
	
	@PostMapping("/ragister")
	@ResponseBody
	public ResponseEntity<String> submitRagistration(@RequestBody UserDTO userDTO,HttpServletRequest httpServletRequest
			,HttpServletResponse httpServletResponse)
	{
	  User user=new User();
	  user.setName(userDTO.getUserName());
	  user.setPassword(userDTO.getPassword());
	  userRepository.save(user);
	  
		return new ResponseEntity<>(gson.toJson(user), HttpStatus.OK);
		
	}
}

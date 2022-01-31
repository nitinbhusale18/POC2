package com.neosoft.springbootsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.springbootsecurity.dto.JwtRequest;
import com.neosoft.springbootsecurity.dto.JwtResponce;
import com.neosoft.springbootsecurity.entity.User;
import com.neosoft.springbootsecurity.service.UserService;
import com.neosoft.springbootsecurity.utill.JwtUtill;


@RestController
public class JwtController {
	
	
	 @Autowired
	 private AuthenticationManager authenticationManager;


	    @Autowired
	    private UserService userService;

	    @Autowired
	    private JwtUtill jwtUtil;

	    @PostMapping(value = "/token")
	    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

	       
	        System.out.println(jwtRequest);
	        try {

	            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getName(), jwtRequest.getPassword()));


	        } catch (UsernameNotFoundException e) {
	            e.printStackTrace();
	            throw new Exception("Bad Credentials");
	        }catch (BadCredentialsException e)
	        {
	            e.printStackTrace();
	            throw new Exception("Bad Credentials");
	        }


	        //fine area..
	        User user = userService.getUserByName(jwtRequest.getName());

	        String token = this.jwtUtil.generateToken(user);
	        System.out.println("JWT " + token);


	        return ResponseEntity.ok(new JwtResponce(token));

	    }

}

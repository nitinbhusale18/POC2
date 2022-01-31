package com.neosoft.springbootsecurity.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ErrorController {
	
	private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);
	
	@GetMapping("/*")
	public ModelAndView error() {
		logger.info("404");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("notfound");
		return modelAndView;
	}
	
	@GetMapping("/access_denied")
	public ModelAndView accessDenied() {
		logger.info("access_denied");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("access-denied");
		return modelAndView;
	}

}

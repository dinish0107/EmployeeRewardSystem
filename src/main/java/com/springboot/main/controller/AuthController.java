package com.springboot.main.controller;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.springboot.main.model.User;
import com.springboot.main.service.UserService;

public class AuthController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/user/login")
	public User login(Principal  principal) {  //u do not process login??????????????? Spring 
		//ask spring, who has logged in?? i vl give u the username 
		String username = principal.getName();
		User user = (User)userService.loadUserByUsername(username);
		return user; 
	}
}
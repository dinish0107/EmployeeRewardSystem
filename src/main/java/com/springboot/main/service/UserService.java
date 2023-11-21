package com.springboot.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.main.model.User;
import com.springboot.main.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User insert(User user) {
		
		return userRepository.save(user);
	}

}
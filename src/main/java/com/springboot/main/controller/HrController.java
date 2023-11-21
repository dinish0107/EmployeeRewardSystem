package com.springboot.main.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Hr;

import com.springboot.main.model.User;
import com.springboot.main.service.HrService;
import com.springboot.main.service.UserService;

@RestController
@RequestMapping("/hr")
public class HrController {

	@Autowired
	private UserService userService;

	@Autowired
	private HrService hrService;
	@Autowired
	private PasswordEncoder passwordEncoder;


	@PostMapping("/add")
	public Hr insertHr(@RequestBody Hr hr) {
		User user = hr.getUser();
		String passwordPlain = user.getPassword();

		String encodedPassword = passwordEncoder.encode(passwordPlain);
		user.setPassword(encodedPassword);

		user.setRole("HR");
		user = userService.insert(user);
		hr.setUser(user);

		return hrService.insert(hr);
	}

	@GetMapping("/all")
	public List<Hr> getAllHr(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "1000000") Integer size) {

		Pageable pageable = PageRequest.of(page, size);
		return hrService.getAllHr(pageable);
	}


	@GetMapping("/getone/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") int id) {

		try {
			Hr hr = hrService.getOne(id);
			return ResponseEntity.ok().body(hr);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}


	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteHr(@PathVariable("id") int id) {

		try {

			Hr hr = hrService.getOne(id);

			hrService.deleteHr(hr);
			return ResponseEntity.ok().body("Hr deleted successfully");

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	

}

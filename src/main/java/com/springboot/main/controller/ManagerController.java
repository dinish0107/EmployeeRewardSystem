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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.enums.RoleType;
import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Address;
import com.springboot.main.model.Employee;
import com.springboot.main.model.Manager;
import com.springboot.main.model.User;
import com.springboot.main.service.AddressService;
import com.springboot.main.service.EmployeeService;
import com.springboot.main.service.ManagerService;
import com.springboot.main.service.UserService;

@RestController
@RequestMapping("/managers")
public class ManagerController {

	@Autowired
	private ManagerService managerService;

	@Autowired
	private UserService userService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmployeeService employeeService;

	// Adding manager details to database....
	@PostMapping("/address/add")
	public Manager insertManager(@RequestBody Manager manager) {
		// save user info in db
		User user = manager.getUser();
		// i am encrypting the password
		String passwordPlain = user.getPassword();

		String encodedPassword = passwordEncoder.encode(passwordPlain);
		user.setPassword(encodedPassword);

		user.setRole(RoleType.MANAGER);
		user = userService.insert(user);
		// attach the saved user(in step 1)
		manager.setUser(user);

		Address address = manager.getAddress();

		address = addressService.insert(address);

		manager.setAddress(address);

		return managerService.insert(manager);
	}

	// fetching the all managers details from database.....

	@GetMapping("/all")
	public List<Manager> getAllManagers(
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "1000000") Integer size) {

		Pageable pageable = PageRequest.of(page, size);
		return managerService.getAllManagers(pageable);
	}

	// fetching single manager details by using their id..
	@GetMapping("/getone/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") int id) {

		try {
			Manager manager = managerService.getById(id);
			return ResponseEntity.ok().body(manager);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

//deleting single manager by using id...

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteManager(@PathVariable("id") int id) {

		try {
			// validate id

			Manager manager = managerService.getById(id);

			// delete
			managerService.deleteManager(manager);
			return ResponseEntity.ok().body("Manager deleted successfully");

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	//if manager wants to update his address then it will works
	
	
	@PutMapping("/update/address/{id}")
	public ResponseEntity<?> updateManagerAddress(@PathVariable("id") int id, @RequestBody Manager newManager) {
		try {
			// validate id
			Manager oldManager = managerService.getById(id);

			Address newAddress = newManager.getAddress();

			if (newAddress != null) {
				// Update address fields
				Address oldAddress = oldManager.getAddress();
				oldAddress.setCity(newAddress.getCity());
				oldAddress.setState(newAddress.getState());
				oldAddress.setCountry(newAddress.getCountry());

				// Save the updated manager
				oldManager = managerService.insert(oldManager);

				return ResponseEntity.ok().body(oldManager);
			} else {
				return ResponseEntity.badRequest().body("New address cannot be null");
			}

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}
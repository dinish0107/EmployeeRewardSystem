package com.springboot.main.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Employee;

import com.springboot.main.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
   private EmployeeRepository employeeRepository;
	
	
	public Employee insert(Employee employee) {
		
		return employeeRepository.save(employee);
	}


	public Employee getById(int id) throws InvalidIdException {
		Optional<Employee> optional =  employeeRepository.findById(id);
		if(!optional.isPresent()){
			throw new InvalidIdException("Employee  ID Invalid");
		}
		return optional.get();
	}


	public List<Employee> getAllEmployees(Pageable pageable) {
		
		return employeeRepository.findAll(pageable).getContent();
	}


	public void deleteEmployee(Employee employee) {
		
		employeeRepository.delete(employee);
	}


	public List<Employee> getEmployeesByManager(int mid) {
		
		return employeeRepository.getEmployeesByManagerjpql(mid);
	}


}
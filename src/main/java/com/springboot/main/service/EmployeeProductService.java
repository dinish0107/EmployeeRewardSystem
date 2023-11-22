package com.springboot.main.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.springboot.main.model.Employee;
import com.springboot.main.model.EmployeeProduct;
import com.springboot.main.model.Product;
import com.springboot.main.repository.EmployeeProductRepository;

@Service
public class EmployeeProductService {

	@Autowired
	private EmployeeProductRepository employeeProductRepository;

	public EmployeeProduct insert(EmployeeProduct employeeProduct) {

		return employeeProductRepository.save(employeeProduct);
	}

}

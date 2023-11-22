package com.springboot.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.dto.EmployeeProductDto;
import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Employee;
import com.springboot.main.model.EmployeeProduct;

import com.springboot.main.model.Product;
import com.springboot.main.service.EmployeeProductService;
import com.springboot.main.service.EmployeeService;
import com.springboot.main.service.ProductService;

@RestController
@RequestMapping("/purchasedproducts")
public class EmployeeProductController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private ProductService productService;
	@Autowired
	private EmployeeProductService employeeProductService;

	
	// if employee buys the multiple products then it calculates total products and total price.
	//those products will be added into purchased products
	
	@PostMapping("/add/{eid}")
	public ResponseEntity<?> purchaseProducts(@PathVariable("eid") int employeeId,
			@RequestBody List<EmployeeProductDto> productsList) {
		try {
			Employee employee = employeeService.getById(employeeId);
			double totalPrice = 0;
			double totalProducts = 0;

			for (EmployeeProductDto employeeproductDto : productsList) {
				Product product = productService.getById(employeeproductDto.getProductId());

				EmployeeProduct employeeProduct = new EmployeeProduct();
				employeeProduct.setEmployee(employee);
				employeeProduct.setProduct(product);

				employeeProduct.setProductname(product.getName());
				employeeProduct.setProductprice(product.getPoints());

				totalProducts += 1;
				totalPrice += product.getPoints();

				employeeProductService.insert(employeeProduct);
			}

			Map<String, Object> response = new HashMap<>();

			response.put("totalProducts", totalProducts);
			response.put("totalPrice", totalPrice);

			return ResponseEntity.ok().body(response);

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}

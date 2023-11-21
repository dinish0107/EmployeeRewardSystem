package com.springboot.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
import com.springboot.main.model.Product;
import com.springboot.main.service.HrService;
import com.springboot.main.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private HrService hrService;

	@Autowired
	private ProductService productService;


	@PostMapping("/add/{hid}")
	public ResponseEntity<?> postProduct(@RequestBody Product product, @PathVariable("hid") int hid) {
		try {
			Hr hr = hrService.getOne(hid);
			product.setHr(hr);
			product = productService.postProduct(product);
			return ResponseEntity.ok().body(product);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/all")
	public List<Product> getAllProducts(
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "1000000") Integer size) {

		Pageable pageable = PageRequest.of(page, size);
		return productService.getAllProducts(pageable);
	}

	@GetMapping("/getone/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") int id) {

		try {
			Product product = productService.getById(id);
			return ResponseEntity.ok().body(product);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteHr(@PathVariable("id") int id) {

		try {

			Product product = productService.getById(id);

			productService.deleteProduct(product);
			return ResponseEntity.ok().body("Product deleted successfully");

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
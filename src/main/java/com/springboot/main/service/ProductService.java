package com.springboot.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Product;
import com.springboot.main.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Product postProduct(Product product) {
		// TODO Auto-generated method stub
		return productRepository.save(product);
	}

	public List<Product> getAllProducts(Pageable pageable) {

		return productRepository.findAll(pageable).getContent();

	}

	public Product getById(int id) throws InvalidIdException {
		Optional<Product> optional = productRepository.findById(id);
		if (!optional.isPresent()) {
			throw new InvalidIdException("Product ID Invalid");
		}
		return optional.get();
	}

	public void deleteProduct(Product product) {

		productRepository.delete(product);
	}
}
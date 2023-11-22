package com.springboot.main.dto;

public class EmployeeProductDto {

	private int productId;
	private String name;
	private double price;

	// Constructors, getters, and setters

	// Default constructor
	public EmployeeProductDto() {
	}

	// Parameterized constructor
	public EmployeeProductDto(int productId, String name, double price) {
		this.productId = productId;
		this.name = name;
		this.price = price;
	}

	// Getters and setters...

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
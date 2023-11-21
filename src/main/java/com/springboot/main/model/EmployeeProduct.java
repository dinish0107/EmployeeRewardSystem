package com.springboot.main.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee_product")
public class EmployeeProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	private Employee employee;

	@ManyToOne
	private Product product;

	@Column(name = "date_of_purchase")
	private LocalDate dateOfPurchase;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDateOfPurchase() {
		return dateOfPurchase;
	}

	public void setDateOfPurchase(LocalDate dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}

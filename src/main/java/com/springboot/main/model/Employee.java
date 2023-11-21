package com.springboot.main.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String email;
	private String empCode;
	private String phone;
	private LocalDate dateofBirth;
	private double pointsBalance;

	@OneToOne
	private User user;

	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	@ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDate getDateofBirth() {
		return dateofBirth;
	}

	public void setDateofBirth(LocalDate dateofBirth) {
		this.dateofBirth = dateofBirth;
	}

	public double getPointsBalance() {
		return pointsBalance;
	}

	public void setPointsBalance(double pointsBalance) {
		this.pointsBalance = pointsBalance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
	

}
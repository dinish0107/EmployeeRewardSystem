package com.springboot.main.dto;

import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.main.model.Address;

public class ManagerDto {

	private String name;
	private String phone;

	@Autowired
	private Address address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
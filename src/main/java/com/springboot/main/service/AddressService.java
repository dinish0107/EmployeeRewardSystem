package com.springboot.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.main.model.Address;
import com.springboot.main.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	public Address insert(Address address) {
		// TODO Auto-generated method stub
		return addressRepository.save(address);
	}

}
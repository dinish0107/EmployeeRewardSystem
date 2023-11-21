package com.springboot.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.main.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
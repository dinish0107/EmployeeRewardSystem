package com.springboot.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.springboot.main.model.EmployeeProduct;

public interface EmployeeProductRepository extends JpaRepository<EmployeeProduct, Integer> {

}
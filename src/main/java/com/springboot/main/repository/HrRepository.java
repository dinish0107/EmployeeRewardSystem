package com.springboot.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.main.model.Hr;



public interface HrRepository extends JpaRepository<Hr, Integer>{

}
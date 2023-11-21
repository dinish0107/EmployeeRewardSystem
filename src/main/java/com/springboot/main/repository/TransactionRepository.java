package com.springboot.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.main.model.Transaction;



public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
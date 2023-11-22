package com.springboot.main.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.springboot.main.model.Transaction;



public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	@Query("select t from Transaction t where t.employee.id=?1")
	List<Transaction> getTransactionsByEmployee(int eid);

}
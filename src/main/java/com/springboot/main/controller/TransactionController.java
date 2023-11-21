package com.springboot.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Employee;

import com.springboot.main.model.Transaction;
import com.springboot.main.service.EmployeeService;

import com.springboot.main.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private EmployeeService employeeService;


	@PostMapping("/add/{eid}")
	public ResponseEntity<?> insertTransaction(@PathVariable("eid") int eid, @RequestBody Transaction transaction) {

		try {

			Employee employee = employeeService.getById(eid);

			transaction.setEmployee(employee);


			transaction = transactionService.insertTransaction(transaction);

			return ResponseEntity.ok().body(transaction);
		} catch (InvalidIdException e) {

			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@GetMapping("/all")
	public List<Transaction> getAllManagers(
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "1000000") Integer size) {

		Pageable pageable = PageRequest.of(page, size);
		return transactionService.getAlltransactions(pageable);
	}

	@GetMapping("/getone/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") int id) {

		try {
			Transaction transaction = transactionService.getById(id);
			return ResponseEntity.ok().body(transaction);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteTransaction(@PathVariable("id") int id) {

		try {

			Transaction transaction = transactionService.getById(id);

			transactionService.deleteTransaction(transaction);
			return ResponseEntity.ok().body("Transaction deleted successfully");

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
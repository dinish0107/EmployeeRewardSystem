package com.springboot.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Employee;
import com.springboot.main.model.Manager;
import com.springboot.main.model.Transaction;
import com.springboot.main.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	



	public Transaction insertTransaction(Transaction transaction) {

		return transactionRepository.save(transaction);
	}

	public List<Transaction> getAlltransactions(Pageable pageable) {

		return transactionRepository.findAll(pageable).getContent();
	}

	public Transaction getById(int id) throws InvalidIdException {
		Optional<Transaction> optional = transactionRepository.findById(id);
		if (!optional.isPresent()) {
			throw new InvalidIdException("Transaction ID Invalid");
		}
		return optional.get();
	}

	public void deleteTransaction(Transaction transaction) {

		transactionRepository.delete(transaction);
	}

	public List<Transaction> getTransactionsByEmployee(int eid) {
		// TODO Auto-generated method stub
		return transactionRepository.getTransactionsByEmployee(eid);
	}



}

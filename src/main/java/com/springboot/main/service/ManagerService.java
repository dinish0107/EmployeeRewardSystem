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
import com.springboot.main.repository.EmployeeRepository;
import com.springboot.main.repository.ManagerRepository;
import com.springboot.main.repository.TransactionRepository;

@Service
public class ManagerService {

	@Autowired
	private ManagerRepository managerRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private EmployeeService employeeService;

	public Manager insert(Manager manager) {

		return managerRepository.save(manager);
	}

	public List<Manager> getAllManagers(Pageable pageable) {

		return managerRepository.findAll(pageable).getContent();
	}

	public void deleteManager(Manager manager) {

		managerRepository.delete(manager);
	}

	public Manager getById(int mid) throws InvalidIdException {
		Optional<Manager> optional = managerRepository.findById(mid);
		if (!optional.isPresent()) {
			throw new InvalidIdException("Manager ID Invalid");
		}
		return optional.get();
	}

	public void managerTransferPoints(int mid, int eid, double pointsToTransfer, String comments)
			throws InvalidIdException {

		Manager manager = getById(mid);
		Employee employee = employeeService.getById(eid);

		if (employee == null) {
			throw new InvalidIdException("Employee with ID " + eid + " not found");
		}

		double oldPoints = employee.getPointsBalance();
		double newPoints = oldPoints + pointsToTransfer;
		employee.setPointsBalance(newPoints);
		employeeRepository.save(employee);
		Transaction transaction = new Transaction();

		transaction.setPointsGiven(pointsToTransfer);
		transaction.setOldPoints(oldPoints);
		transaction.setNewPoints(newPoints);
		transaction.setComments(comments);
		
		transaction.setEmployee(employee);

		transactionRepository.save(transaction);

	}
}

package com.springboot.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.main.exception.InvalidIdException;
import com.springboot.main.model.Employee;
import com.springboot.main.model.EmployeeProduct;
import com.springboot.main.model.Transaction;
import com.springboot.main.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee insert(Employee employee) {

		return employeeRepository.save(employee);
	}

	public List<Employee> getAllEmployees(Pageable pageable) {

		return employeeRepository.findAll(pageable).getContent();
	}

	public void deleteEmployee(Employee employee) {

		employeeRepository.delete(employee);
	}

	public List<Employee> getEmployeesByHr(int hid) {

		return employeeRepository.getEmployeesByHr(hid);
	}

	public Employee getById(int id) throws InvalidIdException {
		Optional<Employee> optional = employeeRepository.findById(id);
		if (!optional.isPresent()) {
			throw new InvalidIdException("Employee  ID Invalid");
		}
		return optional.get();
	}

	public List<EmployeeProduct> getPurchasedProductsByEmployee(int eid) {

		return employeeRepository.findByTheEmployeeId(eid);
	}

	public void employeeTransferPoints(int fromEmployeeId, int toEmployeeId, double pointsToTransfer)
			throws InvalidIdException {

		Employee fromEmployee = getById(fromEmployeeId);
		Employee toEmployee = getById(toEmployeeId);

		// Perform points transfer logic
		if (fromEmployee.getPointsBalance() >= pointsToTransfer) {
			fromEmployee.setPointsBalance(fromEmployee.getPointsBalance() - pointsToTransfer);
			toEmployee.setPointsBalance(toEmployee.getPointsBalance() + pointsToTransfer);

			employeeRepository.save(fromEmployee);
			employeeRepository.save(toEmployee);
		} else {
			throw new InvalidIdException("Insufficient points for transfer");
		}

	}

	public double getpointsbalanace(int eid) {
		// TODO Auto-generated method stub
		return employeeRepository.getpointsbalanace(eid);
	}

}

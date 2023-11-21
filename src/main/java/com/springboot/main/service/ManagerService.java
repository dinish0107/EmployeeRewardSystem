package com.springboot.main.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.main.exception.InvalidIdException;

import com.springboot.main.model.Manager;
import com.springboot.main.repository.ManagerRepository;

@Service
public class ManagerService {

	@Autowired
	private ManagerRepository managerRepository;

	public Manager insert(Manager manager) {

		return managerRepository.save(manager);
	}

	public Manager getById(int id) throws InvalidIdException {
		Optional<Manager> optional = managerRepository.findById(id);
		if (!optional.isPresent()) {
			throw new InvalidIdException("Manager ID Invalid");
		}
		return optional.get();
	}

	public List<Manager> getAllManagers(Pageable pageable) {

		return managerRepository.findAll(pageable).getContent();
	}

	public void deleteManager(Manager manager) {

		managerRepository.delete(manager);
	}

}
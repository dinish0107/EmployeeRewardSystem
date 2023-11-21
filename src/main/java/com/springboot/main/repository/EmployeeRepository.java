package com.springboot.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.main.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query( "select e from Employee e where e.manager.id=?1")
	List<Employee> getEmployeesByManagerjpql(int mid);

}
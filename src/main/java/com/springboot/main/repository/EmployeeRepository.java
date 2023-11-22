package com.springboot.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.main.model.Employee;
import com.springboot.main.model.EmployeeProduct;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("select e from Employee e where e.hr.id=?1")
	List<Employee> getEmployeesByHr(int hid);

	@Query("SELECT ep FROM EmployeeProduct ep WHERE ep.employee.id=?1")
	List<EmployeeProduct> findByTheEmployeeId(int eid);

	@Query("select e.pointsBalance from Employee e where e.id=?1")
	double getpointsbalanace(int eid);

}
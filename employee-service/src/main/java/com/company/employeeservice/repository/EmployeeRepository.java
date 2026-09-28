package com.company.employeeservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.company.employeeservice.entity.Employee;

public interface EmployeeRepository
extends JpaRepository<Employee, Long>,
        JpaSpecificationExecutor<Employee> {
	Optional<Employee> findByEmail(String email);
	
	 List<Employee> findByFirstName(String firstName);
	 List<Employee> findByDepartmentId(Long departmentId);
	 
	 List<Employee> findBySalaryBetween(Double minSalary, Double maxSalary);
	 
	 List<Employee> findByFirstNameContainingIgnoreCase(String firstName);
	 
	 Page<Employee> findAll(Pageable pageable);

	 List<Employee> findByEmailContainingIgnoreCase(String email);
}

	
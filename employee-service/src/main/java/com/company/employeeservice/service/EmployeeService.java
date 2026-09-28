package com.company.employeeservice.service;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.company.employeeservice.client.DepartmentClient;
import com.company.employeeservice.dto.DepartmentDTO;
import com.company.employeeservice.dto.EmployeeRequestDTO;
import com.company.employeeservice.dto.EmployeeResponseDTO;
import com.company.employeeservice.entity.Employee;
import com.company.employeeservice.exception.EmployeeNotFoundException;
import com.company.employeeservice.mapper.EmployeeMapper;
import com.company.employeeservice.repository.EmployeeRepository;
import com.company.employeeservice.service.specification.EmployeeSpecification;

import com.company.employeeservice.kafka.EmployeeEventProducer;
import com.company.employeeservice.event.EmployeeEvent;

@Service
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	
	private final DepartmentClient departmentClient;
		
	private final EmployeeEventProducer employeeEventProducer;
	
		public EmployeeService(
		        EmployeeRepository employeeRepository,
		        DepartmentClient departmentClient, EmployeeEventProducer employeeEventProducer) {

		    this.employeeRepository = employeeRepository;
		    this.departmentClient = departmentClient;
			this.employeeEventProducer = employeeEventProducer;
		}
	
	

	
	
	
	public EmployeeResponseDTO getEmployeeById(Long id) {

	    Employee employee =
	            employeeRepository.findById(id)
	                    .orElseThrow(() ->
	                            new EmployeeNotFoundException(
	                                    "Employee not found with id: " + id));

	    return enrichWithDepartment(employee);
	}


	public EmployeeResponseDTO createEmployee(
	        EmployeeRequestDTO requestDTO) {

	    Employee employee =
	            EmployeeMapper.toEntity(requestDTO);

	    Employee savedEmployee =
	            employeeRepository.save(employee);
	    
	    EmployeeEvent event = new EmployeeEvent(
	            "EMPLOYEE_CREATED",
	            savedEmployee.getId(),	
	            savedEmployee.getFirstName(),
	            savedEmployee.getLastName(),
	            savedEmployee.getEmail(),
	            savedEmployee.getSalary(),
	            savedEmployee.getDepartmentId()
	    );

	    employeeEventProducer.sendEmployeeEvent(event);

	    return enrichWithDepartment(savedEmployee);
	}
	
	public List<EmployeeResponseDTO> getAllEmployees() {

	    List<Employee> employees =
	            employeeRepository.findAll();

	    return employees.stream()
	            .map(this::enrichWithDepartment)
	            .toList();
	}


	public EmployeeResponseDTO updateEmployee(
	        Long id,
	        EmployeeRequestDTO requestDTO) {

	    Employee existingEmployee =
	            employeeRepository.findById(id)
	                    .orElseThrow(() ->
	                            new EmployeeNotFoundException(
	                                    "Employee not found with id: " + id));

	    existingEmployee.setFirstName(requestDTO.getFirstName());
	    existingEmployee.setLastName(requestDTO.getLastName());
	    existingEmployee.setEmail(requestDTO.getEmail());
	    existingEmployee.setSalary(requestDTO.getSalary());
	    existingEmployee.setDepartmentId(
	            requestDTO.getDepartmentId());

	    Employee updatedEmployee =
	            employeeRepository.save(existingEmployee);

	    return enrichWithDepartment(updatedEmployee);
	}	
	
	public void deleteEmployee(Long id) {

	    Employee employee = employeeRepository.findById(id)
	            .orElseThrow(() ->
	                    new EmployeeNotFoundException(
	                            "Employee not found with id: " + id));

	    employeeRepository.delete(employee);
	}
	
	
	public EmployeeResponseDTO getEmployeeByEmail(String email) {

	    Employee employee = employeeRepository.findByEmail(email)
	            .orElseThrow(() ->
	                    new EmployeeNotFoundException(
	                            "Employee not found with email: " + email));

	    return EmployeeMapper.toResponseDTO(employee);
	}
	
	
	public List<EmployeeResponseDTO> getEmployeesByDepartmentId(Long departmentId) {

	    List<Employee> employees =
	            employeeRepository.findByDepartmentId(departmentId);

	    return employees.stream()
	            .map(EmployeeMapper::toResponseDTO)
	            .toList();
	}
	
	public List<EmployeeResponseDTO> searchEmployeesByEmail(String email) {

	    List<Employee> employees =
	            employeeRepository.findByEmailContainingIgnoreCase(email);

	    return employees.stream()
	            .map(EmployeeMapper::toResponseDTO)
	            .toList();
	}
	
	
	public Page<EmployeeResponseDTO> searchEmployees(
	        String name,
	        String email,
	        Long departmentId,
	        Double minSalary,
	        Double maxSalary,
	        Pageable pageable) {

	    Specification<Employee> specification =
	            Specification.where(
	                    EmployeeSpecification.hasName(name))
	                    .and(EmployeeSpecification.hasEmail(email))
	                    .and(EmployeeSpecification.hasDepartmentId(departmentId))
	                    .and(EmployeeSpecification.hasMinSalary(minSalary))
	                    .and(EmployeeSpecification.hasMaxSalary(maxSalary));

	    Page<Employee> employees =
	            employeeRepository.findAll(
	                    specification,
	                    pageable);

	    return employees.map(
	            this::enrichWithDepartment);
	}
	
	public List<EmployeeResponseDTO> getEmployeesBySalaryRange(
	        Double minSalary,
	        Double maxSalary) {

	    List<Employee> employees =
	            employeeRepository.findBySalaryBetween(
	                    minSalary,
	                    maxSalary);

	    return employees.stream()
	            .map(EmployeeMapper::toResponseDTO)
	            .toList();
	}
	
	
	public List<Employee> searchEmployeesByName(String name) {

	    return employeeRepository
	            .findByFirstNameContainingIgnoreCase(name);
	}
	
	public List<EmployeeResponseDTO> getEmployeesByFirstName(String name) {

	    List<Employee> employees =
	            employeeRepository.findByFirstNameContainingIgnoreCase(name);

	    return employees.stream()
	            .map(EmployeeMapper::toResponseDTO)
	            .toList();
	}
	
	
	
	public Page<Employee> getEmployees(Pageable pageable) {
	    return employeeRepository.findAll(pageable);
	}
	
	
	private EmployeeResponseDTO enrichWithDepartment(
	        Employee employee) {

	    EmployeeResponseDTO responseDTO =
	            EmployeeMapper.toResponseDTO(employee);

	    DepartmentDTO department =
	            departmentClient.getDepartmentById(
	                    employee.getDepartmentId());

	    responseDTO.setDepartment(department);

	    return responseDTO;
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

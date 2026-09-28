package com.company.employeeservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.employeeservice.dto.EmployeeRequestDTO;
import com.company.employeeservice.dto.EmployeeResponseDTO;
import com.company.employeeservice.entity.Employee;
import com.company.employeeservice.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
public class EmployeeController {
	
	
	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
		
	}
	
	@GetMapping("/employees")
	public List<EmployeeResponseDTO> getAllEmployees(){	
		return employeeService.getAllEmployees();
	}
    
    
	@GetMapping("/employees/{id}")
	public EmployeeResponseDTO getEmployeeById(
	        @PathVariable("id") Long id) {	

	    return employeeService.getEmployeeById(id);
	}
    
    @PostMapping("/employees")
    public EmployeeResponseDTO addEmployee(@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {
    	
    	return employeeService.createEmployee(employeeRequestDTO);
    }
    
    
    @PutMapping("/employees/{id}")
    public EmployeeResponseDTO updateEmployee(
            @PathVariable("id") Long id,
            @Valid @RequestBody EmployeeRequestDTO requestDTO) {

        return employeeService.updateEmployee(id, requestDTO);
    }
    
    
    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return "Employee deleted successfully";
    }
    
    
    @GetMapping("/employees/email/{email}")
    public EmployeeResponseDTO getEmployeeByEmail(
            @PathVariable("email") String email) {

        return employeeService.getEmployeeByEmail(email);
    }		
    
    @GetMapping("/employees/name/{name}")
    public List<EmployeeResponseDTO> getEmployeesByFirstName(
            @PathVariable("name") String name) {

        return employeeService.getEmployeesByFirstName(name);
    }	
    
    
    @GetMapping("/employees/department/{departmentId}")
    public List<EmployeeResponseDTO> getEmployeesByDepartmentId(
            @PathVariable("departmentId") Long departmentId) {

        return employeeService.getEmployeesByDepartmentId(departmentId);
    }
    
    
    @GetMapping("/employees/salary")
    public List<EmployeeResponseDTO> getEmployeesBySalaryRange(
            @RequestParam Double minSalary,
            @RequestParam Double maxSalary) {

        return employeeService.getEmployeesBySalaryRange(
                minSalary,
                maxSalary);
    }
    
    @GetMapping("/employees/search/email")
    public List<EmployeeResponseDTO> searchEmployeesByEmail(
            @RequestParam String email) {

        return employeeService.searchEmployeesByEmail(email);
    }
    
    @GetMapping("/employees/search")
    public Page<EmployeeResponseDTO> searchEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) Double minSalary,
            @RequestParam(required = false) Double maxSalary,
            Pageable pageable) {

        return employeeService.searchEmployees(
                name,
                email,
                departmentId,
                minSalary,
                maxSalary,
                pageable);
    }
    
    
    
    
    @GetMapping("/employees/search/name")
    public List<Employee> searchEmployeesByName(
            @RequestParam String name) {

        return employeeService.searchEmployeesByName(name);
    }
    
    @GetMapping("/employees/page")
    public Page<Employee> getEmployees(Pageable pageable) {
        return employeeService.getEmployees(pageable);
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
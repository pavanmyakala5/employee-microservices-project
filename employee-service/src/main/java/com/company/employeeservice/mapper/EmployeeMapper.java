package com.company.employeeservice.mapper;

import com.company.employeeservice.dto.EmployeeRequestDTO;
import com.company.employeeservice.dto.EmployeeResponseDTO;
import com.company.employeeservice.entity.Employee;

public class EmployeeMapper {

    public static EmployeeResponseDTO toResponseDTO(Employee employee) {

        EmployeeResponseDTO responseDTO =
                new EmployeeResponseDTO();

        responseDTO.setId(employee.getId());
        responseDTO.setFirstName(employee.getFirstName());
        responseDTO.setLastName(employee.getLastName());
        responseDTO.setEmail(employee.getEmail());
        responseDTO.setSalary(employee.getSalary());
        responseDTO.setDepartmentId(employee.getDepartmentId());

        return responseDTO;
    }

    public static Employee toEntity(
            EmployeeRequestDTO requestDTO) {

        Employee employee = new Employee();

        employee.setFirstName(requestDTO.getFirstName());
        employee.setLastName(requestDTO.getLastName());
        employee.setEmail(requestDTO.getEmail());
        employee.setSalary(requestDTO.getSalary());
        employee.setDepartmentId(
                requestDTO.getDepartmentId());

        return employee;
    }
}
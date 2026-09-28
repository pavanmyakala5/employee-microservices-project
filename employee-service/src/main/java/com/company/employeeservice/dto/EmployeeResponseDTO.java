package com.company.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Double salary;

    private Long departmentId;

    private DepartmentDTO department;
}
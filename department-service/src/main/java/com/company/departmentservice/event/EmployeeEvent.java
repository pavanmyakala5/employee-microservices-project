package com.company.departmentservice.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEvent {

    private String eventType;
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private Double salary;
    private Long departmentId;
}
package com.company.departmentservice.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DepartmentRequestDTO {

    @NotBlank(message = "Department name is required")
    private String name;

    @NotBlank(message = "Department description is required")
    private String description;
}
package com.company.departmentservice.mapper;

import com.company.departmentservice.dto.DepartmentRequestDTO;
import com.company.departmentservice.dto.DepartmentResponseDTO;
import com.company.departmentservice.entity.Department;

public class DepartmentMapper {

    public static Department toEntity(DepartmentRequestDTO requestDTO) {

        Department department = new Department();

        department.setName(requestDTO.getName());
        department.setDescription(requestDTO.getDescription());

        return department;
    }

    public static DepartmentResponseDTO toResponseDTO(Department department) {

        DepartmentResponseDTO responseDTO = new DepartmentResponseDTO();

        responseDTO.setId(department.getId());
        responseDTO.setName(department.getName());
        responseDTO.setDescription(department.getDescription());

        return responseDTO;
    }
}
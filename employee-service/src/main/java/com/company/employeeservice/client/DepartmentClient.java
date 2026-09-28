package com.company.employeeservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.company.employeeservice.dto.DepartmentDTO;

@FeignClient(name = "department-service")
public interface DepartmentClient {

    @GetMapping("/departments/{id}")
    DepartmentDTO getDepartmentById(
            @PathVariable("id") Long id);
}	
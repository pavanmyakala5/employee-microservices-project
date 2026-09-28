package com.company.departmentservice.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.company.departmentservice.dto.DepartmentRequestDTO;
import com.company.departmentservice.dto.DepartmentResponseDTO;
import com.company.departmentservice.service.DepartmentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentResponseDTO createDepartment(
            @Valid @RequestBody DepartmentRequestDTO requestDTO) {

        return departmentService.createDepartment(requestDTO);
    }

    @GetMapping("/{id}")
    public DepartmentResponseDTO getDepartmentById(
            @PathVariable Long id) {

        return departmentService.getDepartmentById(id);
    }

    @GetMapping
    public List<DepartmentResponseDTO> getAllDepartments() {

        return departmentService.getAllDepartments();
    }

    @PutMapping("/{id}")
    public DepartmentResponseDTO updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentRequestDTO requestDTO) {

        return departmentService.updateDepartment(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(
            @PathVariable Long id) {

        departmentService.deleteDepartment(id);
    }
    
    @GetMapping("/search")
    public Page<DepartmentResponseDTO> searchDepartments(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            Pageable pageable) {

        return departmentService.searchDepartments(
                name,
                description,
                pageable);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
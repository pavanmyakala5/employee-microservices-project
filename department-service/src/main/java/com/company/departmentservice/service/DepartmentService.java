package com.company.departmentservice.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.company.departmentservice.dto.DepartmentRequestDTO;
import com.company.departmentservice.dto.DepartmentResponseDTO;
import com.company.departmentservice.entity.Department;
import com.company.departmentservice.exception.DepartmentNotFoundException;
import com.company.departmentservice.mapper.DepartmentMapper;
import com.company.departmentservice.repository.DepartmentRepository;
import com.company.departmentservice.specification.DepartmentSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentResponseDTO createDepartment(
            DepartmentRequestDTO requestDTO) {

        Department department =
                DepartmentMapper.toEntity(requestDTO);

        Department savedDepartment =
                departmentRepository.save(department);

        return DepartmentMapper.toResponseDTO(savedDepartment);
    }

    public DepartmentResponseDTO getDepartmentById(Long id) {

        Department department =
                departmentRepository.findById(id)
                        .orElseThrow(() ->
                                new DepartmentNotFoundException(
                                        "Department not found with id: " + id));

        return DepartmentMapper.toResponseDTO(department);
    }

    public List<DepartmentResponseDTO> getAllDepartments() {

        return departmentRepository.findAll()
                .stream()
                .map(DepartmentMapper::toResponseDTO)
                .toList();
    }

    public DepartmentResponseDTO updateDepartment(
            Long id,
            DepartmentRequestDTO requestDTO) {

        Department existingDepartment =
                departmentRepository.findById(id)
                        .orElseThrow(() ->
                                new DepartmentNotFoundException(
                                        "Department not found with id: " + id));

        existingDepartment.setName(requestDTO.getName());
        existingDepartment.setDescription(
                requestDTO.getDescription());

        Department updatedDepartment =
                departmentRepository.save(existingDepartment);

        return DepartmentMapper.toResponseDTO(updatedDepartment);
    }

    public void deleteDepartment(Long id) {

        Department department =
                departmentRepository.findById(id)
                        .orElseThrow(() ->
                                new DepartmentNotFoundException(
                                        "Department not found with id: " + id));

        departmentRepository.delete(department);
    }

    public Page<DepartmentResponseDTO> searchDepartments(
            String name,
            String description,
            Pageable pageable) {

        Specification<Department> specification =
                Specification.where(
                        DepartmentSpecification.hasName(name))
                        .and(
                        DepartmentSpecification.hasDescription(
                                description));

        Page<Department> departments =
                departmentRepository.findAll(
                        specification,
                        pageable);

        return departments.map(
                DepartmentMapper::toResponseDTO);
    }
}
package com.company.departmentservice.specification;

import org.springframework.data.jpa.domain.Specification;

import com.company.departmentservice.entity.Department;

public class DepartmentSpecification {

    public static Specification<Department> hasName(String name) {

        return (root, query, criteriaBuilder) -> {

            if (name == null || name.isBlank()) {
                return null;
            }

            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")),
                    "%" + name.toLowerCase() + "%"
            );
        };
    }

    public static Specification<Department> hasDescription(
            String description) {

        return (root, query, criteriaBuilder) -> {

            if (description == null || description.isBlank()) {
                return null;
            }

            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("description")),
                    "%" + description.toLowerCase() + "%"
            );
        };
    }
}
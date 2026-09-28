


package com.company.employeeservice.service.specification;

import org.springframework.data.jpa.domain.Specification;

import com.company.employeeservice.entity.Employee;

public class EmployeeSpecification {

    public static Specification<Employee> hasName(String name) {

        return (root, query, criteriaBuilder) -> {

            if (name == null || name.isBlank()) {
                return null;
            }

            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("firstName")),
                    "%" + name.toLowerCase() + "%"
            );
        };
    }
    
    public static Specification<Employee> hasDepartmentId(Long departmentId) {

        return (root, query, criteriaBuilder) -> {

            if (departmentId == null) {
                return null;
            }

            return criteriaBuilder.equal(
                    root.get("departmentId"),
                    departmentId
            );
        };
    }
    
    
    public static Specification<Employee> hasMaxSalary(Double maxSalary) {

        return (root, query, criteriaBuilder) -> {

            if (maxSalary == null) {
                return null;
            }

            return criteriaBuilder.lessThanOrEqualTo(
                    root.get("salary"),
                    maxSalary
            );
        };
    }
    
    
    public static Specification<Employee> hasMinSalary(Double minSalary) {

        return (root, query, criteriaBuilder) -> {

            if (minSalary == null) {
                return null;
            }

            return criteriaBuilder.greaterThanOrEqualTo(
                    root.get("salary"),
                    minSalary
            );
        };
    }
    public static Specification<Employee> hasEmail(String email) {

        return (root, query, criteriaBuilder) -> {

            if (email == null || email.isBlank()) {
                return null;
            }

            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("email")),
                    "%" + email.toLowerCase() + "%"
            );
        };
    }
}
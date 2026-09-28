package com.company.apigateway.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/fallback/employees")
    public ResponseEntity<Map<String, Object>> employeeFallback() {

        Map<String, Object> response = Map.of(
                "service", "employee-service",
                "message", "Employee service is temporarily unavailable",
                "status", 503
        );

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(response);
    }
}
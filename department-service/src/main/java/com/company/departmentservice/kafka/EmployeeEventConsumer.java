package com.company.departmentservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.company.departmentservice.event.EmployeeEvent;

@Service
public class EmployeeEventConsumer {

    @KafkaListener(
        topics = "employee-events",
        groupId = "department-service-group"
    )
    public void consumeEmployeeEvent(EmployeeEvent event) {

        System.out.println("===== KAFKA EVENT RECEIVED =====");

        System.out.println("Event Type     : " + event.getEventType());
        System.out.println("Employee ID    : " + event.getEmployeeId());
        System.out.println("Employee Name  : "
                + event.getFirstName() + " "
                + event.getLastName());
        System.out.println("Email          : " + event.getEmail());
        System.out.println("Department ID  : " + event.getDepartmentId());

        System.out.println("================================");
    }
}
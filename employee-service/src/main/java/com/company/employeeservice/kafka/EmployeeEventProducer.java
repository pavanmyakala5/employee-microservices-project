package com.company.employeeservice.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.company.employeeservice.event.EmployeeEvent;

@Service
public class EmployeeEventProducer {

    private static final String TOPIC = "employee-events";

    private final KafkaTemplate<String, EmployeeEvent> kafkaTemplate;

    public EmployeeEventProducer(
            KafkaTemplate<String, EmployeeEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEmployeeEvent(EmployeeEvent event) {

        kafkaTemplate.send(
                TOPIC,
                event.getEmployeeId().toString(),
                event
        );
    }
}
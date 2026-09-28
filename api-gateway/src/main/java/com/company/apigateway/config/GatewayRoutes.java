package com.company.apigateway.config;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions.circuitBreaker;
import static org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions.lb;
import static org.springframework.web.servlet.function.RequestPredicates.path;

@Configuration
public class GatewayRoutes {

    @Bean
    public RouterFunction<ServerResponse> employeeRoute() {

        return route("employee-service")
                .route(path("/employees/**"), http())
                .filter(lb("EMPLOYEE-SERVICE"))
                .filter(circuitBreaker(
                        "employeeCircuitBreaker",
                        URI.create("forward:/fallback/employees")
                ))
                .build();
    }
}
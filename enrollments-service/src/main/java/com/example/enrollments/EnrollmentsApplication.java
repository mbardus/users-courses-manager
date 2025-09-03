package com.example.enrollments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableDiscoveryClient
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableMongoRepositories(basePackages = "com.example.enrollments.repository")
@ComponentScan(basePackages = {"com.example.enrollments", "com.example.rabbitmq"})
public class EnrollmentsApplication {
    public static void main(String[] args) {
        SpringApplication.run(EnrollmentsApplication.class, args);
    }
}
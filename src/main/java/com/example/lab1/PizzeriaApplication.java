package com.example.lab1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.example.lab1.repository")
public class PizzeriaApplication {
    public static void main(String[] args) {
        SpringApplication.run(PizzeriaApplication.class, args);
    }
} 
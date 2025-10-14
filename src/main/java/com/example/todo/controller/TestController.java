package com.example.todo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;

@RestController
@RequestMapping("/api/test")
public class TestController {
    private final DynamoDbClient dynamoDbClient;

    TestController(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    @GetMapping("/connection")
    public String testConnection() {
        try {
            // Try to list tables from DynamoDB
            ListTablesResponse response = dynamoDbClient.listTables();
            return "✅ Connected to DynamoDB successfully! Tables found: " + response.tableNames();
        } catch (Exception e) {
            return "❌ Connection failed: " + e.getMessage();
        }
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Spring Boot!";
    }

}

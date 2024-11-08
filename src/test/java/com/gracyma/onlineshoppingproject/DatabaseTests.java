package com.gracyma.onlineshoppingproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class DatabaseTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testDatabaseConnection() {
        try {
            jdbcTemplate.execute("SELECT 1");
            System.out.println("Connection to MySQL is successful!");
        } catch (Exception e) {
            System.err.println("Connection to MySQL failed: " + e.getMessage());
            throw e;
        }
    }
}

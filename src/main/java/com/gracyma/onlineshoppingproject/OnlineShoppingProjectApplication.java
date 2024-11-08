package com.gracyma.onlineshoppingproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gracyma.onlineshoppingproject.db.mappers")
public class OnlineShoppingProjectApplication {

    public static void main(String[] args) {

        SpringApplication.run(OnlineShoppingProjectApplication.class, args);
    }

}

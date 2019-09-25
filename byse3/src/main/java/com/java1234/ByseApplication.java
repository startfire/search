package com.java1234;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ByseApplication {
    public static void main(String[] args) {
        SpringApplication.run(ByseApplication.class, args);
    }
}

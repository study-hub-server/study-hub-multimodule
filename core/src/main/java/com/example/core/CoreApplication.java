package com.example.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.core", "io.kr.demo.infra"})
public class CoreApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application-core,application-infra");
        SpringApplication.run(CoreApplication.class, args);
    }

}
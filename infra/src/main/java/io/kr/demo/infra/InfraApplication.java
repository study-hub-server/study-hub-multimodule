package io.kr.demo.infra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InfraApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application-infra");
        SpringApplication.run(InfraApplication.class, args);
    }

}

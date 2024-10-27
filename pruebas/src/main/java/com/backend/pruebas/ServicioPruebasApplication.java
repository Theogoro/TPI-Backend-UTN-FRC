package com.backend.pruebas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.backend.pruebas.repository")
public class ServicioPruebasApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServicioPruebasApplication.class, args);
    }
}
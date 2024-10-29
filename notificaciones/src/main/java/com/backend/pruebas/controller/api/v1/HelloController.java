package com.backend.pruebas.controller.api.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notificaciones")
public class HelloController {
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World";
    }
}
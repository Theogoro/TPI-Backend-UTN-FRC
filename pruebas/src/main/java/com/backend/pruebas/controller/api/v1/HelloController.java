package com.backend.pruebas.controller.api.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.backend.pruebas.model.entity.User;
import com.backend.pruebas.service.UserService;

@RestController
@RequestMapping("/api/v1/pruebas")
public class HelloController {
    @Autowired
    private UserService userService;

    public HelloController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/save/{name}")
    public ResponseEntity<User> saveUser(@RequestParam("name") String userName) {
        // Validar el nombre de usuario
        if (userName == null || userName.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de usuario no puede estar vacío.");
        }

        User user = new User(userName);
        User savedUser;

        try {
            savedUser = userService.saveUser(user);  
        } catch (Exception e) {
            // Manejo de excepciones al guardar el usuario
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el usuario.", e);
        }

        // Verifica si el usuario fue guardado correctamente
        if (savedUser != null) {
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Maneja el caso donde no se pudo guardar
        }
    }
}
package com.backend.pruebas.controller.api.v1;

import com.backend.pruebas.model.dto.PruebaDTO;
import com.backend.pruebas.model.entity.Prueba;
import com.backend.pruebas.service.PruebaService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pruebas")
public class PruebaController {

    @Autowired
    private PruebaService pruebaService;

    @PostMapping("")
    public ResponseEntity<Prueba> newPrueba(@Valid @RequestBody PruebaDTO pruebaDTO) {
        return ResponseEntity.ok(pruebaService.createPrueba(pruebaDTO));
    }

    @GetMapping("")
    public ResponseEntity<List<Prueba>> getPruebas(
        @RequestParam(defaultValue = "0", name = "page") int page,
        @RequestParam(defaultValue = "10", name = "size") int size
    ) {
        return ResponseEntity.ok(pruebaService.getPruebas(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prueba> getPruebaById(@RequestParam(name = "id") int id) {
        return ResponseEntity.ok(pruebaService.getPruebaById(id));
    }
}

package com.backend.pruebas.controller.api.v1;

import com.backend.pruebas.model.dto.PruebaDTO;
import com.backend.pruebas.model.entity.Prueba;
import com.backend.pruebas.service.PruebaService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<PruebaDTO>> getPruebas(
        @RequestParam(defaultValue = "0", name = "page") int page,
        @RequestParam(defaultValue = "10", name = "size") int size
    ) {
        return ResponseEntity.ok(pruebaService.getPruebas(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PruebaDTO> getPruebaById(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(pruebaService.getPruebaById(id));
    }

    @GetMapping("/encurso")
    public ResponseEntity<List<PruebaDTO>> getPruebasEnCurso(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "10", name = "size") int size
    ) {
        return ResponseEntity.ok(pruebaService.getPruebasEnCurso(page, size));
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Prueba> finalizarPrueba(@PathVariable(name = "id") int id,
                                                  @RequestBody String comentario){
        return ResponseEntity.ok(pruebaService.finalizarPrueba(id, comentario));
    }

    @GetMapping("/informe/incidentes")
    public ResponseEntity<List<PruebaDTO>> getPruebasInforme(){
        return ResponseEntity.ok(pruebaService.getPruebasConIncidentes());
    }
    @GetMapping("/informe/incidentesPorEmpleado/{id}")
    public ResponseEntity<List<PruebaDTO>> getPruebasInformePorEmpleado(@PathVariable(name = "id") int id){
        return ResponseEntity.ok(pruebaService.getPruebasConIncidentesPorEmpleado(id));
    }
    @GetMapping("/informe/incidentesPorVehiculo/{id}")
    public ResponseEntity<List<PruebaDTO>> getPruebasInformePorVehiculo(@PathVariable(name = "id") int id){
        return ResponseEntity.ok(pruebaService.getPruebasConIncidentesPorVehiculo(id));
    }
}

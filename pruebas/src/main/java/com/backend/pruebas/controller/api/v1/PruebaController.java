package com.backend.pruebas.controller.api.v1;

import com.backend.pruebas.dto.PruebaDTO;
import com.backend.pruebas.model.entity.Prueba;
import com.backend.pruebas.service.PruebaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/pruebas")
public class PruebaController {

    @Autowired
    private PruebaService pruebaService;

    @PostMapping("/new")
    public ResponseEntity<Prueba> newPrueba(@RequestBody PruebaDTO pruebaDTO) {
        Prueba nuevaPrueba = new Prueba();
        nuevaPrueba.setIdInteresado(pruebaDTO.getInteresadoId());
        nuevaPrueba.setIdVehiculo(pruebaDTO.getVehiculoId());
        nuevaPrueba.setFechaHoraInicio(LocalDateTime.now());
        nuevaPrueba.setIdEmpleado(pruebaDTO.getEmpleadoId());

        return ResponseEntity.ok(pruebaService.crearPrueba(nuevaPrueba));
    }
}

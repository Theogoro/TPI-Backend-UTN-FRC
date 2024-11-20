package com.backend.pruebas.controller.api.v1;

import java.io.ObjectInputFilter.Config;
import java.time.LocalDateTime;

import com.backend.pruebas.exceptions.ResourceNotFoundException;
import com.backend.pruebas.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.pruebas.model.dto.ConfiguracionDTO;
import com.backend.pruebas.model.dto.PosicionDTO;
import com.backend.pruebas.model.entity.Posicion;
import com.backend.pruebas.service.ApiService;
import com.backend.pruebas.service.PosicionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/posiciones")
public class PosicionController {

  @Autowired
  private PosicionService posicionService;
  @Autowired
  private ApiService apiService;
    @Autowired
    private VehiculoRepository vehiculoRepository;

  @PostMapping("")
  public ResponseEntity<Posicion> createPosicion(@Valid @RequestBody PosicionDTO posicion) {
    ConfiguracionDTO configuracion = apiService.getConfiguracion();
    return ResponseEntity.ok(posicionService.savePosicion(posicion, configuracion));
  }

  @GetMapping("/informe/kmrecorridos/{id}/{fechaHoraInicio}/{fechaHoraFin}")
  public double KmRecorridosPorVehiculoEnUnPeriodo(@PathVariable(name = "id") long id,
                                                   @PathVariable(name = "fechaHoraInicio")LocalDateTime fechaHoraInicio,
                                                   @PathVariable(name = "fechaHoraFin")LocalDateTime fechaHoraFin
                                                   ) {
    vehiculoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontro el vehiculo"));
    if (fechaHoraInicio.isAfter(fechaHoraFin)) {
      throw new IllegalArgumentException("La fecha de inicio no debe ser posterior a la fecha de fin.");
    }

    if (fechaHoraInicio.isAfter(LocalDateTime.now())) {
      throw new IllegalArgumentException("La fecha de inicio no debe ser posterior al momento actual.");
    }

    return posicionService.KmRecorridosPorVehiculoEnUnPeriodo(id, fechaHoraInicio,fechaHoraFin);
  }
}

package com.backend.pruebas.controller.api.v1;

import java.io.ObjectInputFilter.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

  @Value("${external.api-url}")
  private String apiUrl;
  
  @PostMapping("")
  public ResponseEntity<Posicion> createPosicion(@Valid @RequestBody PosicionDTO posicion) {
    ConfiguracionDTO configuracion = apiService.callExternalApi(apiUrl, ConfiguracionDTO.class);
    return ResponseEntity.ok(posicionService.savePosicion(posicion, configuracion));
  }

  @GetMapping("")
  public String ok() {
    return apiUrl;
  }
}

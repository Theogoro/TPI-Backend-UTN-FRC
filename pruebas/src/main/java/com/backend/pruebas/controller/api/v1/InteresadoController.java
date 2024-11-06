package com.backend.pruebas.controller.api.v1;

import com.backend.pruebas.service.InteresadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/interesados")
public class InteresadoController {

  private final InteresadoService interesadoService;
  @Autowired
    public InteresadoController(InteresadoService interesadoService) {
        this.interesadoService = interesadoService;
    }
}

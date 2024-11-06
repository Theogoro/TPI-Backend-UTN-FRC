package com.backend.pruebas.controller.api.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/interesados")
public class InteresadoController {
  @Autowired
  private final InteresadoService interesadoService;
}

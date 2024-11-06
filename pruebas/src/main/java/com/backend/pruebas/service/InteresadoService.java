package com.backend.pruebas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.pruebas.model.dto.InteresadoDTO;
import com.backend.pruebas.model.entity.Interesado;
import com.backend.pruebas.repository.InteresadoRepository;

@Service
public class InteresadoService {

  @Autowired
  private InteresadoRepository interesadoRepository;
  

  public InteresadoService(InteresadoRepository interesadoRepository) {
    this.interesadoRepository = interesadoRepository;
  }

  public Interesado createInteresado(InteresadoDTO interesadoDto) {
    Interesado interesado = interesadoDto.toEntity();
    return interesadoRepository.save(interesado);
  }
}

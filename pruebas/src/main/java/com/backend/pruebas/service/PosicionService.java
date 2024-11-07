package com.backend.pruebas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.pruebas.exceptions.BadRequestException;
import com.backend.pruebas.model.dto.ConfiguracionDTO;
import com.backend.pruebas.model.dto.PosicionDTO;
import com.backend.pruebas.model.entity.Posicion;
import com.backend.pruebas.repository.PosicionRepository;
import com.backend.pruebas.repository.VehiculoRepository;
import com.backend.pruebas.utils.MathUtils;


@Service
public class PosicionService {

  private final PosicionRepository posicionRepository;
  private final VehiculoRepository vehiculoRepository;

  @Autowired
  public PosicionService(
      PosicionRepository posicionRepository,
      VehiculoRepository vehiculoRepository
  ) {
    this.posicionRepository = posicionRepository;
    this.vehiculoRepository = vehiculoRepository;
  }

  @Transactional
  public Posicion savePosicion(PosicionDTO posicionDto, ConfiguracionDTO configuracionDto) {
      vehiculoRepository.findById(posicionDto.getIdVehiculo()).orElseThrow(() -> new BadRequestException("El vehiculo no existe"));
      Posicion posicion = posicionRepository.save(posicionDto.toEntity());
      validatePosicion(posicionDto, configuracionDto);
      return posicion;
  }

  private void validatePosicion(PosicionDTO posicion, ConfiguracionDTO configuracion) {
    double radioAdmitidoKm = configuracion.getRadioAdmitidoKm();
    double latitud = posicion.getLatitud();
    double longitud = posicion.getLongitud();
    double latitudAgencia = configuracion.getCoordenadasAgencia().getLat();
    double longitudAgencia = configuracion.getCoordenadasAgencia().getLon();
  
    if (MathUtils.distance(latitud, longitud, latitudAgencia, longitudAgencia) > radioAdmitidoKm) {
      throw new BadRequestException("La posición está fuera del rango permitido");
    }

    for (ConfiguracionDTO.Zona zona : configuracion.getZonasRestringidas()) {
      if (MathUtils.isInsideZone(latitud, longitud, zona.getNoroeste(), zona.getSureste())) {
        throw new BadRequestException("La posición está dentro de una zona restringida");
      }
    }
  }
}

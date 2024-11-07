package com.backend.pruebas.model.dto;

import java.time.LocalDateTime;

import com.backend.pruebas.model.entity.Posicion;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PosicionDTO {
  private Long id;
  @NotNull(message = "El id del vehiculo no puede ser nulo")
  private Long idVehiculo;
  private LocalDateTime fechaHora;
  @NotNull(message = "La latitud no puede ser nula")
  private Double latitud;
  @NotNull(message = "La longitud no puede ser nula")
  private Double longitud;

  public PosicionDTO(Long id, Long idVehiculo, Double latitud, Double longitud) {
    this.id = id;
    this.idVehiculo = idVehiculo;
    this.fechaHora = LocalDateTime.now();
    this.latitud = latitud;
    this.longitud = longitud;
  }

  public PosicionDTO() { }

  public Posicion toEntity() {
    Posicion posicion = new Posicion();
    posicion.setId(id);
    posicion.setIdVehiculo(idVehiculo);
    posicion.setFechaHora(fechaHora);
    posicion.setLatitud(latitud);
    posicion.setLongitud(longitud);
    return posicion;
  }
}

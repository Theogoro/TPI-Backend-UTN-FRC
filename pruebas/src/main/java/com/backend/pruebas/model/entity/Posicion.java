package com.backend.pruebas.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

import com.backend.pruebas.model.dto.ConfiguracionDTO;

@Entity
@Data
@Table(name = "posiciones")
public class Posicion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_vehiculo")
    private Long idVehiculo;
    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;
    private Double latitud;
    private Double longitud;
}

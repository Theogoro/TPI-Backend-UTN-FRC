package com.backend.pruebas.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

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
    private Long latitud;
    private Long longitud;
}

package com.backend.pruebas.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Posicion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "id_vehiculo")
    private Vehiculo vehiculo;
    private LocalDateTime fechaHora;
    private Long latitud;
    private Long longitud;
}

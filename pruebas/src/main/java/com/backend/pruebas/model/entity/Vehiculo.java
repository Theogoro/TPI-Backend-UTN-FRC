package com.backend.pruebas.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Table(name = "vehiculos")
@NoArgsConstructor
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String patente;
    @Column(name = "anio")
    private int anio;
    @Column(name = "id_modelo")
    private Long idModelo;
}

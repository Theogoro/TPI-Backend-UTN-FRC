package com.backend.pruebas.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Table(name = "vehiculo")
@NoArgsConstructor
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String patente;
    private int anio;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_modelo")
    private Modelo modelo;
    @OneToMany(mappedBy = "vehiculo")
    private Set<Prueba> pruebas;
    @OneToOne(mappedBy = "vehiculo")
    private Posiciones posicion;

}

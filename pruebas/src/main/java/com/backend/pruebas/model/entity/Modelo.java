package com.backend.pruebas.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Table(name = "modelo")
@NoArgsConstructor
public class Modelo {
    private long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name  = "id_marca")
    private Marca marca;
    private String descripcion;
    @OneToMany(mappedBy = "modelo")
    private Set<Vehiculo> vehiculos;
}

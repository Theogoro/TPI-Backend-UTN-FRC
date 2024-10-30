package com.backend.pruebas.model.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "pruebas")
@NoArgsConstructor
public class Prueba {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_vehiculo")
    private Vehiculo vehiculo;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_empleado")
    private Employee empleado;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_interesado")
    private Interesado interesado;
    private LocalDateTime fecha_hora_inicio;
    private LocalDateTime fecha_hora_fin;
    private String comentarios;
}

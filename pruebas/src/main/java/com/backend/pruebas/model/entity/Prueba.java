package com.backend.pruebas.model.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "pruebas")
@NoArgsConstructor
@Data
public class Prueba {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "id_vehiculo")
    private Long idVehiculo;
    @Column(name = "id_empleado")
    private Long idEmpleado;
    @Column(name = "id_interesado")
    private Long idInteresado;
    @Column(name = "fecha_hora_inicio")
    private LocalDateTime fechaHoraInicio;
    @Column(name = "fecha_hora_fin")
    private LocalDateTime fechaHoraFin;
    private String comentarios;
}

package com.backend.notificaciones.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;
    private String mensaje;
    @Column(name = "id_empleado")
    private Long idEmpleado;
    @Column(name = "id_interesado")
    private Long idInteresado;
}

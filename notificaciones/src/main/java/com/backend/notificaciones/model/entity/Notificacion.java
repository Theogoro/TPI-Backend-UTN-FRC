package com.backend.notificaciones.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String motivo;
    private String subject;
    private String mensaje;
    private String destinatario;
}

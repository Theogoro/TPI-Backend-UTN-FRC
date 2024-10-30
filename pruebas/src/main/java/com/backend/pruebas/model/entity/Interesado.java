package com.backend.pruebas.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "interesados")
public class Interesados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_documento", nullable = false)
    private String tipoDocumento;

    @Column(name = "documento", nullable = false)
    private String documento;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "restringido", nullable = false)
    private boolean restringido;

    @Column(name = "nro_licencia", nullable = false)
    private Long nroLicencia;

    @Column(name = "fecha_vencimiento_licencia", nullable = false)
    private LocalDateTime fechaVencimientoLicencia;

    @Column(name = "email", nullable = false, unique = true)
    private String email;


}


package com.backend.pruebas.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "interesados")
public class Interesado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoDocumento;

    private String documento;

    private String nombre;

    private String apellido;

    private boolean restringido;

    @Column(name = "nro_licencia", nullable = false)
    private Long nroLicencia;

    @Column(name = "fecha_vencimiento_licencia", nullable = false)
    private LocalDateTime fechaVencimientoLicencia;

    private String email;


}


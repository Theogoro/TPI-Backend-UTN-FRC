package com.backend.pruebas.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

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

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "idInteresado")
    private Set<Prueba> pruebas;

    public boolean isLicenciaVencida(){
        return this.fechaVencimientoLicencia.isBefore(LocalDateTime.now());
    }

    public boolean isRestringido(){
        return this.restringido;
    }

    public boolean isValid(){
        return !isLicenciaVencida() && !isRestringido();
    }
}


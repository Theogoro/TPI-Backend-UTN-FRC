package com.backend.pruebas.model.entity;

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
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_empleado")
    private Employee empleado;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_interesado")
    private Interesado interesado;

}

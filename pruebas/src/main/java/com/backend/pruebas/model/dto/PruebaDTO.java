package com.backend.pruebas.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import jakarta.validation.constraints.*;

import com.backend.pruebas.model.entity.Prueba;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PruebaDTO {
    @NotNull(message = "El id del interesado no puede ser vacio")
    private Long interesadoId;
    @NotNull(message = "El id del vehiculo no puede ser vacio")
    private Long vehiculoId;
    @NotNull(message = "El id del empleado no puede ser vacio")
    private Long empleadoId;
    private LocalDateTime fechaHoraInicio;
    private int id;
    private LocalDateTime fechaHoraFin;
    private String comentario;
    private boolean tuvoIncidente;


    public Prueba toEntity() {
        Prueba prueba = new Prueba();
        prueba.setIdInteresado(interesadoId);
        prueba.setIdVehiculo(vehiculoId);
        prueba.setFechaHoraInicio(LocalDateTime.now());
        prueba.setIdEmpleado(empleadoId);
        prueba.setTuvoIncidente(tuvoIncidente);
        prueba.setComentario(comentario);
        prueba.setFechaHoraFin(fechaHoraFin);
        
        return prueba;
    }

    public PruebaDTO(Prueba prueba) {
        this.id = prueba.getId();
        this.interesadoId = prueba.getIdInteresado();
        this.vehiculoId = prueba.getIdVehiculo();
        this.empleadoId = prueba.getIdEmpleado();
        this.fechaHoraInicio = prueba.getFechaHoraInicio();
        this.fechaHoraFin = prueba.getFechaHoraFin();
        this.comentario = prueba.getComentario();
        this.tuvoIncidente = prueba.isTuvoIncidente();
    }
}

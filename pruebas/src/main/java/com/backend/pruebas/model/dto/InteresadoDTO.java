package com.backend.pruebas.model.dto;

import java.time.LocalDateTime;

import com.backend.pruebas.model.entity.Interesado;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InteresadoDTO {
    private Long id; // Incluido si es necesario devolverlo al cliente
    @NotEmpty(message = "El tipo de documento no puede estar vacío")
    private String tipoDocumento;
    @NotEmpty(message = "El documento no puede estar vacío")
    private String documento;
    @NotEmpty(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotEmpty(message = "El apellido no puede estar vacío")
    private String apellido;
    @NotNull(message = "El campo restringido no puede estar vacío")
    private boolean restringido;
    @NotNull(message = "El número de licencia no puede estar vacío")
    private Long nroLicencia;
    @NotNull(message = "La fecha de vencimiento de la licencia no puede estar vacía")
    private LocalDateTime fechaVencimientoLicencia;
    @NotEmpty(message = "El email no puede estar vacío")
    private String email;

    // Método para determinar si la licencia está vencida
    public boolean isLicenciaVencida() {
        return this.fechaVencimientoLicencia.isBefore(LocalDateTime.now());
    }

    public Interesado toEntity() {
        Interesado interesado = new Interesado();
        interesado.setId(this.id);
        interesado.setTipoDocumento(this.tipoDocumento);
        interesado.setDocumento(this.documento);
        interesado.setNombre(this.nombre);
        interesado.setApellido(this.apellido);
        interesado.setRestringido(this.restringido);
        interesado.setNroLicencia(this.nroLicencia);
        interesado.setFechaVencimientoLicencia(this.fechaVencimientoLicencia);
        interesado.setEmail(this.email);
        return interesado;
    }
}
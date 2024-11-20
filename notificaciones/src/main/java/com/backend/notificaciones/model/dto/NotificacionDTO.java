package com.backend.notificaciones.model.dto;

import com.backend.notificaciones.model.entity.Notificacion;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotificacionDTO {
    private String to;
    private String subject;
    private String motivo;
    private String body;

    public Notificacion toEntity() {
        Notificacion notificacion = new Notificacion();
        notificacion.setMensaje(body);
        notificacion.setMotivo(motivo);
        notificacion.setDestinatario(to);
        return notificacion;
    }

    public NotificacionDTO(Notificacion notificacion) {
        this.to = notificacion.getDestinatario();
        this.subject = notificacion.getSubject();
        this.motivo = notificacion.getMotivo();
        this.body = notificacion.getMensaje();
    }
}

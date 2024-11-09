package com.backend.notificaciones.model.dto;

import lombok.Data;

@Data
public class NotificacionDTO {
    private String to;
    private String subject;
    private String body;
}

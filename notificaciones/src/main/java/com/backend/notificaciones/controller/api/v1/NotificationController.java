package com.backend.notificaciones.controller.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.backend.notificaciones.model.dto.NotificacionDTO;
import com.backend.notificaciones.service.MailService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {
  private MailService mailService;

  @Autowired
  public NotificationController(MailService mailService) {
    this.mailService = mailService;
  }

  @GetMapping()
  public ResponseEntity<List<NotificacionDTO>> getNotifications( @RequestParam(defaultValue = "0", name = "page") int page,
                                                                 @RequestParam(defaultValue = "10", name = "size") int size)
  {
    return ResponseEntity.ok(mailService.getNotifications(page, size));
  }

  @PreAuthorize("hasRole('EMPLEADO')")
  @PostMapping("/mail")
  public void sendMail(@RequestBody NotificacionDTO notificacion) {
    mailService.sendMail(notificacion.getTo(), notificacion.getSubject(), notificacion.getBody(), notificacion.getMotivo());
    mailService.saveNotificacion(notificacion.toEntity());
  }
}

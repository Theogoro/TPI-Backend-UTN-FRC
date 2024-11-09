package com.backend.notificaciones.controller.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.notificaciones.model.dto.NotificacionDTO;
import com.backend.notificaciones.service.MailService;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {
  private MailService mailService;

  @Autowired
  public NotificationController(MailService mailService) {
    this.mailService = mailService;
  }

  @PostMapping("/mail")
  public void sendMail(@RequestBody NotificacionDTO notificacion) {
    mailService.sendMail(notificacion.getTo(), notificacion.getSubject(), notificacion.getBody());
  }
}

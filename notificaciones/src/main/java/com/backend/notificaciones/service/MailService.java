package com.backend.notificaciones.service;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MailService {
  @Value("${mail.smtp.host}")
  private String host;
  @Value("${mail.smtp.port}")
  private int port;
  @Value("${mail.smtp.user}")
  private String user;
  @Value("${mail.smtp.password}")
  private String password;

  public void sendMail(String to, String subject, String body) {
    Properties props = new Properties();
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.port", port);
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");

    System.out.println("Sending email to " + to + " with subject " + subject);

    // Crear el autenticador usando Jakarta Mail
    Authenticator auth = new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password);
      }
    };

    Session session = Session.getInstance(props, auth);

    try {
      Message message = new javax.mail.internet.MimeMessage(session);
      message.setFrom(new javax.mail.internet.InternetAddress(user));
      message.setRecipients(Message.RecipientType.TO, javax.mail.internet.InternetAddress.parse(to));
      message.setSubject(subject);
      message.setText(body);

      Transport.send(message);
    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }
  }
}

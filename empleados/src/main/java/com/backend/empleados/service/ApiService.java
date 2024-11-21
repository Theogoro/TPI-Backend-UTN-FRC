package com.backend.empleados.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    @Value("${external.gateway-url}")
    private String URL_CONF;

    private final RestTemplate restTemplate;

    @Autowired
    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> T callExternalApi(String url, Class<T> responseType) {
        return restTemplate.getForObject(url, responseType);
    }

    public void notifyEmployeeBadTest(Long id, String email) {
        NotifyEmployeePayload payload = new NotifyEmployeePayload(
            id,
            "Incidente - Se registró una mala prueba",
            "Incidente - Se registró una mala prueba",
            email
        );

        restTemplate.postForEntity(URL_CONF + "/api/v1/notifications/mail", payload, Object.class);
    }

}

class NotifyEmployeePayload {
    private Long id;
    private String subject;
    private String motivo;
    private String to;
    private String body;

    public NotifyEmployeePayload(Long id, String subject, String motivo, String to) {
        this.id = id;
        this.subject = subject;
        this.motivo = motivo;
        this.to = to;
        this.body = "Se registró una mala prueba para el empleado con id " + id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

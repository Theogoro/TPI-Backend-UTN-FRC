package com.backend.pruebas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.backend.pruebas.model.dto.ConfiguracionDTO;

@Service
public class ApiService {

    @Value("${external.api-url}")
    private String URL_CONF;

    private final RestTemplate restTemplate;

    @Autowired
    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> T callExternalApi(String url, Class<T> responseType) {
        return restTemplate.getForObject(url, responseType);
    }

    public ConfiguracionDTO getConfiguracion() {
        return callExternalApi(URL_CONF, ConfiguracionDTO.class);
    }

    public boolean employeeExists(Long id) {
        try {
            return restTemplate.getForEntity(URL_CONF + "/api/v1/employees/" + id, Object.class).getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            if (e.getMessage().contains("404")) {
                return false;
            }
            throw e;
        }
    }

    public void notifyEmployee(Long id) {
        // restTemplate.postForEntity(URL_CONF + "/api/v1/employees/" + id + "/notify", null, Object.class);
    }
}

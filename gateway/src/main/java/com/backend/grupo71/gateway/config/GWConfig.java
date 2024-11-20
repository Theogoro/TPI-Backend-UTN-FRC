package com.backend.grupo71.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GWConfig {
    @Bean
    public RouteLocator configurarRutas(RouteLocatorBuilder builder,
                                        @Value("${api-gw.url-microservicio-employees}") String uriEmployees,
                                        @Value("${api-gw.url-microservicio-pruebas}") String uriPruebas,
                                        @Value("${api-gw.url-microservicio-notificaciones}") String uriNotificaciones)  {
        return builder.routes()
                // Ruteo al Microservicio de Personas
                .route(p -> p.path("/api/v1/employees/**").uri(uriEmployees))
                // Ruteo al Microservicio de Entradas
                .route(p -> p.path("/api/v1/pruebas/**").uri(uriPruebas))
                // Ruteo al Microservicio Notificaciones
                .route(p -> p.path("/api/v1/notifications/**").uri(uriNotificaciones))
                .build();

    }

}

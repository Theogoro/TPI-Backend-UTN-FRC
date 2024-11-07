package com.backend.pruebas.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class ConfiguracionDTO {

    private Coordenadas coordenadasAgencia;
    private double radioAdmitidoKm;
    private List<Zona> zonasRestringidas;

    @Data
    public static class Coordenadas {
        private double lat;
        private double lon;
    }

    @Data
    public static class Zona {
        private Coordenadas noroeste;
        private Coordenadas sureste;
    }
}

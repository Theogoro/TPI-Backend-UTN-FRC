package com.backend.pruebas.utils;

import com.backend.pruebas.model.dto.ConfiguracionDTO.Coordenadas;

public class MathUtils {
  /**
   * Calcula la distancia entre dos puntos geográficos usando algebra en el plano
   * @param lat1
   * @param lon1
   * @param lat2
   * @param lon2
   * @return
   */
  // public static double distance(double lat1, double lon1, double lat2, double lon2) {
      // final double KM_PER_DEGREE_LAT = 111.0;
  //   double diffLat = lat2 - lat1;
  //   double diffLon = lon2 - lon1;
  //   return Math.sqrt(diffLat * diffLat + diffLon * diffLon);
  // }

  public static double distance(double lat1, double lon1, double lat2, double lon2) {
    // Aproximación de kilómetros por grado de latitud y longitud en el ecuador
    final double KM_PER_DEGREE_LAT = 111.0;
    
    // Calcular la distancia en latitud y longitud
    double diffLatKm = (lat2 - lat1) * KM_PER_DEGREE_LAT;
    double avgLat = Math.toRadians((lat1 + lat2) / 2);
    double kmPerDegreeLon = KM_PER_DEGREE_LAT * Math.cos(avgLat);
    double diffLonKm = (lon2 - lon1) * kmPerDegreeLon;

    // Aplicar la fórmula de distancia euclidiana
    return Math.sqrt(diffLatKm * diffLatKm + diffLonKm * diffLonKm);
}

  public static boolean isInsideZone(double latitud, double longitud, Coordenadas noroeste, Coordenadas sureste) {
    return latitud <= noroeste.getLat() && latitud >= sureste.getLat() && longitud >= noroeste.getLon() && longitud <= sureste.getLon();
  }

}

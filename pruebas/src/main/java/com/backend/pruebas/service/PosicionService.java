package com.backend.pruebas.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.pruebas.exceptions.BadRequestException;
import com.backend.pruebas.model.dto.ConfiguracionDTO;
import com.backend.pruebas.model.dto.PosicionDTO;
import com.backend.pruebas.model.entity.Posicion;
import com.backend.pruebas.model.entity.Prueba;
import com.backend.pruebas.model.entity.Vehiculo;
import com.backend.pruebas.repository.InteresadoRepository;
import com.backend.pruebas.repository.PosicionRepository;
import com.backend.pruebas.repository.PruebaRepository;
import com.backend.pruebas.repository.VehiculoRepository;
import com.backend.pruebas.utils.MathUtils;


@Service
public class PosicionService {

  private final PosicionRepository posicionRepository;
  private final VehiculoRepository vehiculoRepository;
  private final InteresadoRepository interesadoRepository;
  private final PruebaRepository pruebaRepository;
  private final PruebaService pruebaService;
  private final ApiService apiService;

  @Autowired
  public PosicionService(
      PosicionRepository posicionRepository,
      VehiculoRepository vehiculoRepository,
      InteresadoRepository interesadoRepository,
      PruebaRepository pruebaRepository,
      PruebaService pruebaService,
      ApiService apiService
  ) {
    this.posicionRepository = posicionRepository;
    this.vehiculoRepository = vehiculoRepository;
    this.interesadoRepository = interesadoRepository;
    this.pruebaRepository = pruebaRepository;
    this.pruebaService = pruebaService;
    this.apiService = apiService;
  }

  @Transactional
  public Posicion savePosicion(PosicionDTO posicionDto, ConfiguracionDTO configuracionDto) {
      vehiculoRepository.findById(posicionDto.getIdVehiculo()).orElseThrow(() -> new BadRequestException("El vehiculo no existe"));
      Posicion posicion = posicionRepository.save(posicionDto.toEntity());
      if (!validatePosicion(posicionDto, configuracionDto)) {
        Optional<Prueba> prueba = pruebaRepository.findFirstByVehiculoIdOrderByFechaHoraInicioDesc(posicionDto.getIdVehiculo());
        if (prueba.isPresent() && prueba.get().getFechaHoraFin() == null) {
          restrigirInteresado(prueba.get().getIdInteresado());
          tuvoIncidente(prueba.get().getId());
          apiService.notifyEmployee(prueba.get().getIdEmpleado());
        }
      }
      return posicion;
  }


  private void restrigirInteresado(Long idInteresado) {
    interesadoRepository.findById(idInteresado).ifPresent(interesado -> {
      if (interesado.isValid()) {
        interesado.setRestringido(true);
        interesadoRepository.save(interesado);
      }
    });
  }
  private void tuvoIncidente(int idPrueba) {
    pruebaRepository.findById(idPrueba).ifPresent(prueba -> {
        prueba.setTuvoIncidente(true);
        pruebaRepository.save(prueba);
      }
    );
  }

  private boolean validatePosicion(PosicionDTO posicion, ConfiguracionDTO configuracion) {
    double radioAdmitidoKm = configuracion.getRadioAdmitidoKm();
    double latitud = posicion.getLatitud();
    double longitud = posicion.getLongitud();
    double latitudAgencia = configuracion.getCoordenadasAgencia().getLat();
    double longitudAgencia = configuracion.getCoordenadasAgencia().getLon();
  
    if (MathUtils.distance(latitud, longitud, latitudAgencia, longitudAgencia) > radioAdmitidoKm) {
      return false;
    }
    for (ConfiguracionDTO.Zona zona : configuracion.getZonasRestringidas()) {
      if (MathUtils.isInsideZone(latitud, longitud, zona.getNoroeste(), zona.getSureste())) {
        return false;
      }
    }

    return true;
  }

  public double KmRecorridosPorVehiculoEnUnPeriodo(long idVehiculo, LocalDateTime fechaHoraInicio,LocalDateTime fechaHoraFin) {
    List<Posicion> posiciones = posicionRepository.findByIdVehiculoAndFechaHoraIsBetween(idVehiculo, fechaHoraInicio, fechaHoraFin);
    double distanciaTotal = 0;
    for (int i = 0; i < posiciones.size() - 1; i++) {
      Posicion pos1 = posiciones.get(i);
      Posicion pos2 = posiciones.get(i + 1);
      distanciaTotal += MathUtils.distance(pos1.getLatitud(),pos1.getLongitud(), pos2.getLatitud(), pos2.getLongitud());
    }
    return distanciaTotal;
  }
}

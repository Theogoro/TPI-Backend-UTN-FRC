package com.backend.pruebas.service;

import com.backend.pruebas.model.entity.Interesado;
import com.backend.pruebas.model.entity.Prueba;
import com.backend.pruebas.model.entity.Vehiculo;
import com.backend.pruebas.repository.InteresadoRepository;
import com.backend.pruebas.repository.PruebaRepository;
import com.backend.pruebas.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PruebaService {

    @Autowired
    private InteresadoRepository interesadoRepository;
    @Autowired
    private PruebaRepository pruebaRepository;
    @Autowired
    private VehiculoRepository vehiculoRepository;



    public Prueba crearPrueba(Prueba prueba ) {

        // Validar Interesado
//        Interesado interesado = interesadoRepository.findById(prueba.getIdInteresado()).orElse(null);
//        if (interesado == null) {
//            throw new RuntimeException("No se encontro el interesado");
//        }
//        if (interesado.isLicenciaVencida()) {
//            throw new RuntimeException("La licencia del intersado esta vencida");
//        }
//        if (interesado.isRestringido()) {
//            throw new RuntimeException("El interesado esta restringido");
//        }

        // Validar vehículo
        Vehiculo vehiculo = vehiculoRepository.findById(prueba.getIdVehiculo()).orElse(null);
        Optional<Prueba> pruebas = pruebaRepository.findByIdVehiculoAndFechaHoraFinIsNull(prueba.getIdVehiculo());
//        if (vehiculo == null) {
//            throw new RuntimeException("No se encontro el vehiculo");
//        }
        if (pruebas.isPresent()) {
           throw new RuntimeException("El vehiculo esta en una prueba");
        }

        return pruebaRepository.save(prueba);
    }
}

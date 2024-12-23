package com.backend.pruebas.service;

import com.backend.pruebas.model.dto.PruebaDTO;
import com.backend.pruebas.model.entity.Interesado;
import com.backend.pruebas.model.entity.Prueba;
import com.backend.pruebas.model.entity.Vehiculo;
import com.backend.pruebas.repository.InteresadoRepository;
import com.backend.pruebas.repository.PruebaRepository;
import com.backend.pruebas.repository.VehiculoRepository;

import com.backend.pruebas.exceptions.BadRequestException;
import com.backend.pruebas.exceptions.ResourceNotFoundException;

import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
// import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class PruebaService {

    @Autowired
    private InteresadoRepository interesadoRepository;
    @Autowired
    private PruebaRepository pruebaRepository;
    @Autowired
    private VehiculoRepository vehiculoRepository;
    @Autowired
    private ApiService apiService;


    public Prueba createPrueba(PruebaDTO pruebaDto) {
        Prueba prueba = pruebaDto.toEntity();
        Interesado interesado = interesadoRepository
                    .findById(prueba.getIdInteresado())
                    .orElseThrow(() -> new BadRequestException("No se encontro el interesado"));

        if (!interesado.isValid()) {
            throw new BadRequestException("El interesado no cumple con los requisitos (Licencia vencida o restringido)");
        }
        // TODO: Ver que el cliente no este en una prueba activa

        Vehiculo vehiculo = vehiculoRepository.findById(prueba.getIdVehiculo()).orElseThrow(() -> new BadRequestException("No se encontro el vehiculo"));
        Optional<Prueba> pruebas = pruebaRepository.findByIdVehiculoAndFechaHoraFinIsNull(vehiculo.getId());

        if (pruebas.isPresent()) {
            throw new BadRequestException("El vehiculo esta en una prueba");
        }

        if (apiService.employeeExists(prueba.getIdEmpleado())) {
            throw new BadRequestException("El empleado no existe");
        }

        return pruebaRepository.save(prueba);
    }

    public List<PruebaDTO> getPruebas(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return pruebaRepository.findAll(pageRequest)
                               .getContent()
                               .stream()
                               .map(PruebaDTO::new)
                               .collect(Collectors.toList()); // Convierte el stream a una lista
    }
    

    public PruebaDTO getPruebaById(int id) {
        return new PruebaDTO(pruebaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontro la prueba")));
    }
    public List<PruebaDTO> getPruebasEnCurso(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return pruebaRepository.findByFechaHoraFinIsNull(pageRequest).stream().map(PruebaDTO::new).collect(Collectors.toList());
    }

    public Prueba finalizarPrueba(int id, String comentario) {
        Prueba prueba = pruebaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontro la prueba"));
        prueba.setComentario(comentario);
        prueba.setFechaHoraFin(LocalDateTime.now());
        return pruebaRepository.save(prueba);
    }

    public List<PruebaDTO> getPruebasConIncidentes() {
        return pruebaRepository.findByTuvoIncidente(true).stream().map(PruebaDTO::new).collect(Collectors.toList());
    }

    public List<PruebaDTO> getPruebasConIncidentesPorEmpleado(long id) {
        apiService.employeeExists(id);
        return pruebaRepository.findByTuvoIncidenteAndIdEmpleado(true,id).stream().map(PruebaDTO::new).collect(Collectors.toList());
    }

    public List<PruebaDTO> getPruebasConIncidentesPorVehiculo(long id) {
        vehiculoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontro el vehiculo"));
        return pruebaRepository.findByTuvoIncidenteAndIdVehiculo(true, id).stream().map(PruebaDTO::new).collect(Collectors.toList());
    }
}

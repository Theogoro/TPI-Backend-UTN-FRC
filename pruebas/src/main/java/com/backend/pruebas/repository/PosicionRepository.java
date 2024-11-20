package com.backend.pruebas.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.pruebas.model.entity.Posicion;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PosicionRepository extends JpaRepository<Posicion, Integer> {
    List<Posicion> findByIdVehiculoAndFechaHoraIsBetween(int idVehiculo, LocalDateTime fechaHoraMin, LocalDateTime fechaHoraMax);
}

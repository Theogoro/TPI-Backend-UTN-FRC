package com.backend.pruebas.repository;

import com.backend.pruebas.model.entity.Prueba;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PruebaRepository extends JpaRepository<Prueba, Integer>, PagingAndSortingRepository<Prueba, Integer> {

    Optional<Prueba> findByIdVehiculoAndFechaHoraFinIsNull(Long idVehiculo);
    List<Prueba> findByFechaHoraFinIsNull(PageRequest pageRequest);
}

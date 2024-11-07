package com.backend.pruebas.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.pruebas.model.entity.Posicion;

@Repository
public interface PosicionRepository extends JpaRepository<Posicion, Integer> {
}

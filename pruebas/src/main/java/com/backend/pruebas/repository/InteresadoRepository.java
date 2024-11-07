package com.backend.pruebas.repository;

import com.backend.pruebas.model.entity.Interesado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteresadoRepository  extends JpaRepository<Interesado, Long>{
}

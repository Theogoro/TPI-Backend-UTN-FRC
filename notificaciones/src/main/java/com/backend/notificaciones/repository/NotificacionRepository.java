package com.backend.notificaciones.repository;

import com.backend.notificaciones.model.entity.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long>{
}

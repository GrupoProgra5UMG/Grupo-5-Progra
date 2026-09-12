package com.mycompany.citasmedicas.repository;

import com.mycompany.citasmedicas.model.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {
}
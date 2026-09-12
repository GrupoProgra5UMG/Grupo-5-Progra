package com.mycompany.citasmedicas.repository;

import com.mycompany.citasmedicas.model.CitaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CitaMedicaRepository extends JpaRepository<CitaMedica, Long> {
    List<CitaMedica> findByPaciente_Id(Long pacienteId);
    List<CitaMedica> findByMedico_UsuarioIdAndFechaCita(Long medicoId, java.time.LocalDate fecha);
}
package com.mycompany.citasmedicas.repository;

import com.mycompany.citasmedicas.model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HorarioRepository extends JpaRepository<Horario, Long> {
    List<Horario> findByMedico_UsuarioIdAndDiaSemanaAndDisponibleTrue(Long medicoId, Byte diaSemana);
}
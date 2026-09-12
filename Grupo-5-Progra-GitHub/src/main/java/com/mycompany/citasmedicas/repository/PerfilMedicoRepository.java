package com.mycompany.citasmedicas.repository;

import com.mycompany.citasmedicas.model.PerfilMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PerfilMedicoRepository extends JpaRepository<PerfilMedico, Long> {
    List<PerfilMedico> findByEspecialidad_Id(Long especialidadId);
}
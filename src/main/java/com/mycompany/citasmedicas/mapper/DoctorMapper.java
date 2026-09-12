package com.mycompany.citasmedicas.mapper;

import com.mycompany.citasmedicas.dto.DoctorResponse;
import com.mycompany.citasmedicas.model.PerfilMedico;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    public DoctorResponse toResponse(PerfilMedico medico) {
        return new DoctorResponse(
                medico.getUsuarioId(),
                medico.getUsuario().getNombre(),
                medico.getUsuario().getApellido(),
                medico.getEspecialidad().getNombre(),
                medico.getNumColegiado()
        );
    }
}

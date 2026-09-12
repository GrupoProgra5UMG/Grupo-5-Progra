package com.mycompany.citasmedicas.mapper;

import com.mycompany.citasmedicas.dto.CitaRequest;
import com.mycompany.citasmedicas.dto.CitaResponse;
import com.mycompany.citasmedicas.model.CitaMedica;
import com.mycompany.citasmedicas.model.PerfilMedico;
import com.mycompany.citasmedicas.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class CitaMapper {

    public CitaMedica toEntity(CitaRequest request, Usuario paciente, PerfilMedico medico) {
        CitaMedica cita = new CitaMedica();
        cita.setPaciente(paciente);
        cita.setMedico(medico);
        cita.setFechaCita(request.getFecha());
        cita.setHoraCita(request.getHora());
        cita.setMotivo(request.getMotivo().trim());
        cita.setEstado(CitaMedica.EstadoCita.PENDIENTE);
        return cita;
    }

    public CitaResponse toResponse(CitaMedica cita) {
        return new CitaResponse(
                cita.getId(),
                cita.getPaciente().getNombre() + " " + cita.getPaciente().getApellido(),
                cita.getMedico().getUsuario().getNombre() + " " + cita.getMedico().getUsuario().getApellido(),
                cita.getFechaCita(),
                cita.getHoraCita(),
                cita.getEstado().name(),
                cita.getMotivo(),
                cita.getDiagnostico(),
                cita.getReceta()
        );
    }
}

package com.mycompany.citasmedicas.mapper;

import com.mycompany.citasmedicas.dto.HorarioRequest;
import com.mycompany.citasmedicas.dto.HorarioResponse;
import com.mycompany.citasmedicas.model.Horario;
import com.mycompany.citasmedicas.model.PerfilMedico;
import org.springframework.stereotype.Component;

@Component
public class HorarioMapper {

    public Horario toEntity(HorarioRequest request, PerfilMedico medico) {
        Horario horario = new Horario();
        horario.setMedico(medico);
        horario.setDiaSemana(request.getDiaSemana());
        horario.setHoraInicio(request.getHoraInicio());
        horario.setHoraFin(request.getHoraFin());
        horario.setDisponible(true);
        return horario;
    }

    public HorarioResponse toResponse(Horario horario) {
        return new HorarioResponse(
                horario.getId(),
                horario.getDiaSemana(),
                horario.getHoraInicio(),
                horario.getHoraFin(),
                horario.isDisponible()
        );
    }
}

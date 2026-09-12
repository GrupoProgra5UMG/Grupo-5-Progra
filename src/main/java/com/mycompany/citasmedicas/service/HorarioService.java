package com.mycompany.citasmedicas.service;

import com.mycompany.citasmedicas.dto.HorarioRequest;
import com.mycompany.citasmedicas.dto.HorarioResponse;
import com.mycompany.citasmedicas.exception.BusinessRuleException;
import com.mycompany.citasmedicas.exception.ResourceNotFoundException;
import com.mycompany.citasmedicas.model.Horario;
import com.mycompany.citasmedicas.model.PerfilMedico;
import com.mycompany.citasmedicas.repository.HorarioRepository;
import com.mycompany.citasmedicas.repository.PerfilMedicoRepository;
import org.springframework.stereotype.Service;
import com.mycompany.citasmedicas.model.CitaMedica;
import com.mycompany.citasmedicas.repository.CitaMedicaRepository;
import com.mycompany.citasmedicas.mapper.HorarioMapper;
import java.time.LocalTime;

import java.time.LocalDate;
import java.util.List;

@Service
public class HorarioService {

 private final HorarioRepository horarioRepository;
private final PerfilMedicoRepository perfilMedicoRepository;
private final CitaMedicaRepository citaMedicaRepository;
private final HorarioMapper horarioMapper;

public HorarioService(HorarioRepository horarioRepository,
                       PerfilMedicoRepository perfilMedicoRepository,
                       CitaMedicaRepository citaMedicaRepository,
                       HorarioMapper horarioMapper) {
    this.horarioRepository = horarioRepository;
    this.perfilMedicoRepository = perfilMedicoRepository;
    this.citaMedicaRepository = citaMedicaRepository;
    this.horarioMapper = horarioMapper;
}

    public HorarioResponse crearHorario(Long medicoId, HorarioRequest request) {

        PerfilMedico medico = perfilMedicoRepository.findById(medicoId)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil de medico no encontrado"));

        if (!request.getHoraInicio().isBefore(request.getHoraFin())) {
            throw new BusinessRuleException("La hora de inicio debe ser anterior a la hora de fin");
        }

        List<Horario> existentes = horarioRepository
                .findByMedico_UsuarioIdAndDiaSemanaAndDisponibleTrue(medicoId, request.getDiaSemana());

        boolean seSolapa = existentes.stream().anyMatch(h ->
                request.getHoraInicio().isBefore(h.getHoraFin()) &&
                h.getHoraInicio().isBefore(request.getHoraFin())
        );

        if (seSolapa) {
            throw new BusinessRuleException("Ya existe un horario que se interfiere en ese dia y rango de horario");
        }

        Horario horario = horarioMapper.toEntity(request, medico);
        Horario guardado = horarioRepository.save(horario);

        return horarioMapper.toResponse(guardado);
    }

    
    public List<LocalTime> consultarDisponibilidad(Long medicoId, LocalDate fecha) {

    perfilMedicoRepository.findById(medicoId)
            .orElseThrow(() -> new ResourceNotFoundException("Perfil de medico no encontrado"));

    byte diaSemana = (byte) fecha.getDayOfWeek().getValue();

    List<Horario> horarios = horarioRepository
            .findByMedico_UsuarioIdAndDiaSemanaAndDisponibleTrue(medicoId, diaSemana);

    List<CitaMedica> citasDelDia = citaMedicaRepository
            .findByMedico_UsuarioIdAndFechaCita(medicoId, fecha);

    List<LocalTime> horasOcupadas = citasDelDia.stream()
            .filter(c -> c.getEstado() != CitaMedica.EstadoCita.CANCELADA)
            .map(CitaMedica::getHoraCita)
            .toList();

    int duracionSlotMinutos = 30;
    List<LocalTime> disponibles = new java.util.ArrayList<>();

    for (Horario h : horarios) {
        LocalTime actual = h.getHoraInicio();
        while (actual.plusMinutes(duracionSlotMinutos).compareTo(h.getHoraFin()) <= 0) {
            if (!horasOcupadas.contains(actual)) {
                disponibles.add(actual);
            }
            actual = actual.plusMinutes(duracionSlotMinutos);
        }
    }

    return disponibles;
}
    
    
    
}
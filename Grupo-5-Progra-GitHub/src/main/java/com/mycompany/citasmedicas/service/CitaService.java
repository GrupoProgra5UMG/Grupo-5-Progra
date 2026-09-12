package com.mycompany.citasmedicas.service;

import com.mycompany.citasmedicas.dto.CitaRequest;
import com.mycompany.citasmedicas.dto.CitaResponse;
import com.mycompany.citasmedicas.dto.DiagnosticoRequest;
import com.mycompany.citasmedicas.exception.BusinessRuleException;
import com.mycompany.citasmedicas.exception.ResourceNotFoundException;
import com.mycompany.citasmedicas.model.CitaMedica;
import com.mycompany.citasmedicas.model.Horario;
import com.mycompany.citasmedicas.model.PerfilMedico;
import com.mycompany.citasmedicas.model.Usuario;
import com.mycompany.citasmedicas.mapper.CitaMapper;
import com.mycompany.citasmedicas.repository.CitaMedicaRepository;
import com.mycompany.citasmedicas.repository.HorarioRepository;
import com.mycompany.citasmedicas.repository.PerfilMedicoRepository;
import com.mycompany.citasmedicas.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;

@Service
public class CitaService {

    private final CitaMedicaRepository citaMedicaRepository;
    private final PerfilMedicoRepository perfilMedicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final HorarioRepository horarioRepository;
    private final CitaMapper citaMapper;

    public CitaService(CitaMedicaRepository citaMedicaRepository,
                        PerfilMedicoRepository perfilMedicoRepository,
                        UsuarioRepository usuarioRepository,
                        HorarioRepository horarioRepository,
                        CitaMapper citaMapper) {
        this.citaMedicaRepository = citaMedicaRepository;
        this.perfilMedicoRepository = perfilMedicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.horarioRepository = horarioRepository;
        this.citaMapper = citaMapper;
    }

    public CitaResponse crearCita(String correoPaciente, CitaRequest request) {

        Usuario paciente = usuarioRepository.findByCorreo(correoPaciente)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado"));

        PerfilMedico medico = perfilMedicoRepository.findById(request.getMedicoId())
                .orElseThrow(() -> new ResourceNotFoundException("Medico no encontrado"));

        byte diaSemana = (byte) request.getFecha().getDayOfWeek().getValue();

        List<Horario> horariosDelDia = horarioRepository
                .findByMedico_UsuarioIdAndDiaSemanaAndDisponibleTrue(request.getMedicoId(), diaSemana);

        boolean dentroDeHorario = horariosDelDia.stream().anyMatch(h ->
                !request.getHora().isBefore(h.getHoraInicio()) &&
                request.getHora().isBefore(h.getHoraFin())
        );

        if (!dentroDeHorario) {
            throw new BusinessRuleException("El medico no atiende en ese horario");
        }

        List<CitaMedica> citasExistentes = citaMedicaRepository
                .findByMedico_UsuarioIdAndFechaCita(request.getMedicoId(), request.getFecha());

        boolean yaOcupado = citasExistentes.stream().anyMatch(c ->
                c.getHoraCita().equals(request.getHora()) &&
                c.getEstado() != CitaMedica.EstadoCita.CANCELADA
        );

        if (yaOcupado) {
            throw new BusinessRuleException("Ya existe una cita agendada en ese horario");
        }

        CitaMedica cita = citaMapper.toEntity(request, paciente, medico);
        CitaMedica guardada = citaMedicaRepository.save(cita);

        return citaMapper.toResponse(guardada);
    }

    public List<CitaResponse> historialPaciente(String correoPaciente) {
        Usuario paciente = usuarioRepository.findByCorreo(correoPaciente)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado"));

        return citaMedicaRepository.findByPaciente_Id(paciente.getId()).stream()
                .map(citaMapper::toResponse)
                .toList();
    }

    public CitaResponse registrarDiagnostico(Long citaId, DiagnosticoRequest request) {
        CitaMedica cita = citaMedicaRepository.findById(citaId)
                .orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada"));

        cita.setDiagnostico(request.getDiagnostico());
        cita.setReceta(request.getReceta());
        cita.setEstado(CitaMedica.EstadoCita.COMPLETADA);

        CitaMedica actualizada = citaMedicaRepository.save(cita);
        return citaMapper.toResponse(actualizada);
    }

}
package com.mycompany.citasmedicas.service;

import com.mycompany.citasmedicas.dto.DoctorResponse;
import com.mycompany.citasmedicas.model.PerfilMedico;
import com.mycompany.citasmedicas.mapper.DoctorMapper;
import com.mycompany.citasmedicas.repository.PerfilMedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final PerfilMedicoRepository perfilMedicoRepository;
    private final DoctorMapper doctorMapper;

    public DoctorService(PerfilMedicoRepository perfilMedicoRepository, DoctorMapper doctorMapper) {
        this.perfilMedicoRepository = perfilMedicoRepository;
        this.doctorMapper = doctorMapper;
    }

    public List<DoctorResponse> listarMedicos(Long especialidadId) {
        List<PerfilMedico> medicos;

        if (especialidadId != null) {
            medicos = perfilMedicoRepository.findByEspecialidad_Id(especialidadId);
        } else {
            medicos = perfilMedicoRepository.findAll();
        }

        return medicos.stream()
                .map(doctorMapper::toResponse)
                .toList();
    }

}
package com.mycompany.citasmedicas.controller;

import com.mycompany.citasmedicas.dto.DoctorResponse;
import com.mycompany.citasmedicas.service.DoctorService;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
@Validated
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponse>> listar(
            @RequestParam(required = false)
            @Positive(message = "El id de la especialidad debe ser positivo") Long especialidadId) {

        return ResponseEntity.ok(doctorService.listarMedicos(especialidadId));
    }
}

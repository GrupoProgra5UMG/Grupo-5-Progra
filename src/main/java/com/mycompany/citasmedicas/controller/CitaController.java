package com.mycompany.citasmedicas.controller;

import com.mycompany.citasmedicas.dto.CitaRequest;
import com.mycompany.citasmedicas.dto.CitaResponse;
import com.mycompany.citasmedicas.dto.DiagnosticoRequest;
import com.mycompany.citasmedicas.service.CitaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
@Validated
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @PostMapping
    public ResponseEntity<CitaResponse> crear(
            Authentication authentication,
            @Valid @RequestBody CitaRequest request) {

        CitaResponse response = citaService.crearCita(authentication.getName(), request);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/my-history")
    public ResponseEntity<List<CitaResponse>> miHistorial(Authentication authentication) {
        return ResponseEntity.ok(citaService.historialPaciente(authentication.getName()));
    }

    @PutMapping("/{id}/diagnosis")
    public ResponseEntity<CitaResponse> registrarDiagnostico(
            @PathVariable @Positive(message = "El id de la cita debe ser positivo") Long id,
            @Valid @RequestBody DiagnosticoRequest request) {

        return ResponseEntity.ok(citaService.registrarDiagnostico(id, request));
    }
}

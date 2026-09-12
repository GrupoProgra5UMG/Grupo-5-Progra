package com.mycompany.citasmedicas.controller;

import com.mycompany.citasmedicas.dto.HorarioRequest;
import com.mycompany.citasmedicas.dto.HorarioResponse;
import com.mycompany.citasmedicas.service.HorarioService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/schedules")
@Validated
public class HorarioController {

    private final HorarioService horarioService;

    public HorarioController(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    @PostMapping("/{medicoId}")
    public ResponseEntity<HorarioResponse> crear(
            @PathVariable @Positive(message = "El id del médico debe ser positivo") Long medicoId,
            @Valid @RequestBody HorarioRequest request) {

        HorarioResponse response = horarioService.crearHorario(medicoId, request);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/available")
    public ResponseEntity<List<LocalTime>> disponibilidad(
            @RequestParam @NotNull @Positive(message = "El id del médico debe ser positivo") Long medicoId,
            @RequestParam
            @NotNull(message = "La fecha es obligatoria")
            @FutureOrPresent(message = "La fecha no puede ser anterior a hoy")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {

        return ResponseEntity.ok(horarioService.consultarDisponibilidad(medicoId, fecha));
    }
}

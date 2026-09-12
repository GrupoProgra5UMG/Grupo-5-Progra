package com.mycompany.citasmedicas.dto;

import java.time.LocalTime;

public class HorarioResponse {

    private Long id;
    private Byte diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private boolean disponible;

    public HorarioResponse(Long id, Byte diaSemana, LocalTime horaInicio, LocalTime horaFin, boolean disponible) {
        this.id = id;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.disponible = disponible;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Byte getDiaSemana() { return diaSemana; }
    public void setDiaSemana(Byte diaSemana) { this.diaSemana = diaSemana; }

    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }

    public LocalTime getHoraFin() { return horaFin; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
}
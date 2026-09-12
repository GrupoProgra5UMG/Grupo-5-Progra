package com.mycompany.citasmedicas.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class CitaResponse {

    private Long id;
    private String pacienteNombre;
    private String medicoNombre;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;
    private String motivo;
    private String diagnostico;
    private String receta;

    public CitaResponse(Long id, String pacienteNombre, String medicoNombre, LocalDate fecha,
                         LocalTime hora, String estado, String motivo, String diagnostico, String receta) {
        this.id = id;
        this.pacienteNombre = pacienteNombre;
        this.medicoNombre = medicoNombre;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.receta = receta;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPacienteNombre() { return pacienteNombre; }
    public void setPacienteNombre(String pacienteNombre) { this.pacienteNombre = pacienteNombre; }

    public String getMedicoNombre() { return medicoNombre; }
    public void setMedicoNombre(String medicoNombre) { this.medicoNombre = medicoNombre; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    public String getReceta() { return receta; }
    public void setReceta(String receta) { this.receta = receta; }
}
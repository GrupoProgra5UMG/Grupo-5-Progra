package com.mycompany.citasmedicas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DiagnosticoRequest {

    @NotBlank(message = "El diagnóstico es obligatorio")
    @Size(max = 5000, message = "El diagnóstico no puede superar los 5000 caracteres")
    private String diagnostico;

    @Size(max = 5000, message = "La receta no puede superar los 5000 caracteres")
    private String receta;

    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }
    public String getReceta() { return receta; }
    public void setReceta(String receta) { this.receta = receta; }
}

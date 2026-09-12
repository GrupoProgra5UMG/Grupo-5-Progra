package com.mycompany.citasmedicas.dto;

public class DoctorResponse {

    private Long id;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String numColegiado;

    public DoctorResponse(Long id, String nombre, String apellido, String especialidad, String numColegiado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.numColegiado = numColegiado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getNumColegiado() { return numColegiado; }
    public void setNumColegiado(String numColegiado) { this.numColegiado = numColegiado; }
}
package com.mycompany.citasmedicas.dto;

import java.util.List;

public class LoginResponse {

    private String token;
    private String tipo = "Bearer";
    private String correo;
    private String nombre;
    private List<String> roles;

    public LoginResponse(String token, String correo, String nombre, List<String> roles) {
        this.token = token;
        this.correo = correo;
        this.nombre = nombre;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
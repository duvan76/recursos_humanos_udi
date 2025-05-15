package com.udi.rrhh.modelo;

import java.util.Date;

public class GrupoFamiliar {
    private int grupoFamiliarId;
    private String numeroIdentificacionFuncionario;
    private String nombres;
    private String apellidos;
    private Date fechaNacimiento;
    private String rol;

    // Constructores, getters y setters
    public GrupoFamiliar() {
    }

    public GrupoFamiliar(int grupoFamiliarId, String numeroIdentificacionFuncionario, String nombres, String apellidos, Date fechaNacimiento, String rol) {
        this.grupoFamiliarId = grupoFamiliarId;
        this.numeroIdentificacionFuncionario = numeroIdentificacionFuncionario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.rol = rol;
    }

    public int getGrupoFamiliarId() {
        return grupoFamiliarId;
    }

    public void setGrupoFamiliarId(int grupoFamiliarId) {
        this.grupoFamiliarId = grupoFamiliarId;
    }

    public String getNumeroIdentificacionFuncionario() {
        return numeroIdentificacionFuncionario;
    }

    public void setNumeroIdentificacionFuncionario(String numeroIdentificacionFuncionario) {
        this.numeroIdentificacionFuncionario = numeroIdentificacionFuncionario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return nombres + " " + apellidos + " (" + rol + ")";
    }
}

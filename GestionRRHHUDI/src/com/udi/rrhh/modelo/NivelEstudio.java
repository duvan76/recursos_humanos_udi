package com.udi.rrhh.modelo;


public class NivelEstudio {
    private int nivelEstudioId;
    private String nombre;

    // Constructores, getters y setters
    public NivelEstudio() {
    }

    public NivelEstudio(int nivelEstudioId, String nombre) {
        this.nivelEstudioId = nivelEstudioId;
        this.nombre = nombre;
    }

    public int getNivelEstudioId() {
        return nivelEstudioId;
    }

    public void setNivelEstudioId(int nivelEstudioId) {
        this.nivelEstudioId = nivelEstudioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}

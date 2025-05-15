package com.udi.rrhh.modelo;

public class Universidad {
    private int universidadId;
    private String nombre;

    // Constructores, getters y setters
    public Universidad() {
    }

    public Universidad(int universidadId, String nombre) {
        this.universidadId = universidadId;
        this.nombre = nombre;
    }

    public int getUniversidadId() {
        return universidadId;
    }

    public void setUniversidadId(int universidadId) {
        this.universidadId = universidadId;
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
 

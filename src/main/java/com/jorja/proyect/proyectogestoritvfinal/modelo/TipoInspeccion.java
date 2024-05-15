package com.jorja.proyect.proyectogestoritvfinal.modelo;

public class TipoInspeccion {
    private int id;
    private String Nombre;
    private float precio;

    public TipoInspeccion(int id, String nombre, float precio) {
        this.id = id;
        Nombre = nombre;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}

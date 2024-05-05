package com.jorja.proyect.proyectogestoritvfinal.modelo;

public class Usuario {
    private int id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String contraseña;
    private boolean administrador;
    private String FechaAlta;

    public Usuario(int id, String nombre, String apellido, String telefono, String correo, String contraseña, boolean administrador, String fechaAlta) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.contraseña = contraseña;
        this.administrador = administrador;
        FechaAlta = fechaAlta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public String getFechaAlta() {
        return FechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        FechaAlta = fechaAlta;
    }
}

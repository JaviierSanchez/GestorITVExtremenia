package com.jorja.proyect.proyectogestoritvfinal.modelo;

public class Vehiculo {

    private String matricula;
    private String marca;
    private String modelo;
    private String year;
    private int tipoVehiculoId;
    private int usuarioId;

    public Vehiculo(String matricula, String marca, String modelo, String year, int tipoVehiculoId, int usuarioId) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.year = year;
        this.tipoVehiculoId = tipoVehiculoId;
        this.usuarioId = usuarioId;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getTipoVehiculoId() {
        return tipoVehiculoId;
    }

    public void setTipoVehiculoId(int tipoVehiculoId) {
        this.tipoVehiculoId = tipoVehiculoId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}

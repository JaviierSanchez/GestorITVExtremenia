package com.jorja.proyect.proyectogestoritvfinal.modelo;

public class Cita {

    private int id;
    private String matriculaVehiculo;
    private String fecha;
    private String hora;
    private String tipoInspeccionId;
    private String tipoVehiculoId;
    private String precio;
    private boolean activa;


    public Cita(int id, String matriculaVehiculo, String fecha, String hora, String tipoInspeccionId, String tipoVehiculoId, String precio, boolean activa) {
        this.id = id;
        this.matriculaVehiculo = matriculaVehiculo;
        this.fecha = fecha;
        this.hora = hora;
        this.tipoInspeccionId = tipoInspeccionId;
        this.tipoVehiculoId = tipoVehiculoId;
        this.precio = precio;
        this.activa = activa;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getMatriculaVehiculo() {
        return matriculaVehiculo;
    }


    public void setMatriculaVehiculo(String matriculaVehiculo) {
        this.matriculaVehiculo = matriculaVehiculo;
    }


    public String getFecha() {
        return fecha;
    }


    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    public String getHora() {
        return hora;
    }


    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipoInspeccionId() {
        return tipoInspeccionId;
    }

    public void setTipoInspeccionId(String tipoInspeccionId) {
        this.tipoInspeccionId = tipoInspeccionId;
    }

    public String getTipoVehiculoId() {
        return tipoVehiculoId;
    }

    public void setTipoVehiculoId(String tipoVehiculoId) {
        this.tipoVehiculoId = tipoVehiculoId;
    }

    public String getPrecio() {
        return precio;
    }


    public void setPrecio(String precio) {
        this.precio = precio;
    }


    public boolean isActiva() {
        return activa;
    }


    public void setActiva(boolean activa) {
        this.activa = activa;
    }
}



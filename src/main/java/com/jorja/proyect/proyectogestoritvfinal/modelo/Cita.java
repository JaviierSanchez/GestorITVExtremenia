package com.jorja.proyect.proyectogestoritvfinal.modelo;

public class Cita {

    private int id;
    private String matriculaVehiculo;
    private String Fecha;
    private String Hora;
    private String TipoInspeccionId;
    private String TipoVehiculoId;
    private String precio;
    private boolean activa;


    public Cita(int id, String matriculaVehiculo, String fecha, String hora, String tipoInspeccionId, String tipoVehiculoId, String precio, boolean activa) {
        this.id = id;
        this.matriculaVehiculo = matriculaVehiculo;
        Fecha = fecha;
        Hora = hora;
        TipoInspeccionId = tipoInspeccionId;
        TipoVehiculoId = tipoVehiculoId;
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
        return Fecha;
    }


    public void setFecha(String fecha) {
        Fecha = fecha;
    }


    public String getHora() {
        return Hora;
    }


    public void setHora(String hora) {
        Hora = hora;
    }


    public String getTipoInspeccionId() {
        return TipoInspeccionId;
    }


    public void setTipoInspeccionId(String tipoInspeccionId) {
        TipoInspeccionId = tipoInspeccionId;
    }


    public String getTipoVehiculoId() {
        return TipoVehiculoId;
    }


    public void setTipoVehiculoId(String tipoVehiculoId) {
        TipoVehiculoId = tipoVehiculoId;
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



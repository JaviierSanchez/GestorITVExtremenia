package com.jorja.proyect.proyectogestoritvfinal.modelo;

public class HistorialCita {


    private String matricula;
    private String fecha;
    private String hora;
    private String tipoInspeccionId;

    public HistorialCita(String matricula, String fecha, String hora, String tipoInspeccionId) {
        this.matricula = matricula;
        this.fecha = fecha;
        this.hora = hora;
        this.tipoInspeccionId = tipoInspeccionId;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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
}

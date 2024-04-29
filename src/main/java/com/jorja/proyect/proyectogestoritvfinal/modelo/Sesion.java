package com.jorja.proyect.proyectogestoritvfinal.modelo;

public class Sesion {

    private static Usuario usuarioActual;

    public static void iniciarSesion(Usuario usuario){
        usuarioActual = usuario;
    }
    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }
}

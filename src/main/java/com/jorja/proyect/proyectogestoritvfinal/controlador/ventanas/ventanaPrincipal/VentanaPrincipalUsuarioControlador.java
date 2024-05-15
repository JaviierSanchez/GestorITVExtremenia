package com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaPrincipal;

import com.jorja.proyect.proyectogestoritvfinal.modelo.Sesion;
import com.jorja.proyect.proyectogestoritvfinal.modelo.Usuario;
import javafx.scene.control.Label;

public class VentanaPrincipalUsuarioControlador {

    public static void sacarNombreUsuarioLogueado(Label label){
        // Sacar nombre usuario que ha iniciado sesion
        Usuario usuarioActual = Sesion.getUsuarioActual();
        if (usuarioActual != null) {
            label.setText(usuarioActual.getNombre());
        } else {
            label.setText("Usuario desconodido");
        }
    }
}

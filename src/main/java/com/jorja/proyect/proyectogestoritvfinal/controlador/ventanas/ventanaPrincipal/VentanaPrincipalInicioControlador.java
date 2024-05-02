package com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaPrincipal;

import com.jorja.proyect.proyectogestoritvfinal.controlador.bbdd.CONEXIONBD;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentanaPrincipalInicioControlador {


    /***
     *  Metodo que segun el boton presionado se selecciona el layout que
     *  se va a mostrar y se pone el estilo al boton seleccionado
     * @param botonPresionado
     * @param botones
     * @param layouts
     */
    public static void cambiarVentana(Button botonPresionado, Button[] botones, Pane[] layouts) {
        for (int i = 0; i < botones.length; i++) {
            if (botonPresionado == botones[i]) {
                layouts[i].setVisible(true);
                botones[i].setStyle("-fx-background-color: #21666C; -fx-background-radius: 15px; -fx-cursor: hand;");
            } else {
                layouts[i].setVisible(false);
                botones[i].setStyle("-fx-background-color: none; -fx-cursor: hand;");
            }
        }
    }

    /***
     * Metodo
     * @param sql
     * @param label
     * @param cbd
     */
    public static void contadorTarjetas(String sql, Label label, CONEXIONBD cbd) {
        try (Connection conexion = cbd.abrirConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            if (resultado.next()) {
                int total = resultado.getInt("total");
                label.setText(String.valueOf(total));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

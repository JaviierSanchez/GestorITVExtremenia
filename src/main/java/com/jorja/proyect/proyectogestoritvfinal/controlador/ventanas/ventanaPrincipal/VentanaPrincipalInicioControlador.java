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

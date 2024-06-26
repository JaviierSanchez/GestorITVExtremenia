package com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaPrincipal;

import com.jorja.proyect.proyectogestoritvfinal.controlador.bbdd.CONEXIONBD;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormatSymbols;

public class VentanaPrincipalInicioControlador {
    private static Connection conexion;
    private static PreparedStatement sentencia;
    private static ResultSet resultado;

    /***
     * Metodo para sacar el total de total que se mostrara en las tarjetas de contadores
     * @param sql
     * @param label
     * @param cbd
     */
    public static void contadorTarjetas(String sql, Label label, CONEXIONBD cbd) {

        conexion = cbd.abrirConexion();
        try {
            sentencia = conexion.prepareStatement(sql);
            resultado = sentencia.executeQuery();

            if (resultado.next()) {
                int total = resultado.getInt("total");
                label.setText(String.valueOf(total));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para obtener el nombre del mes a partir del número del mes
    public static String obtenerNombreMes(int numeroMes) {
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] nombresMeses = dfs.getMonths();
        return nombresMeses[numeroMes - 1];
    }
}

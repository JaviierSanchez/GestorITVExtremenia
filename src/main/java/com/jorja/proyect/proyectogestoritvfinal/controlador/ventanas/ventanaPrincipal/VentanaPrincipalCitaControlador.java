package com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaPrincipal;

import com.jorja.proyect.proyectogestoritvfinal.controlador.bbdd.CONEXIONBD;
import com.jorja.proyect.proyectogestoritvfinal.modelo.TipoInspeccion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentanaPrincipalCitaControlador {

    private static Connection conexion;
    private static PreparedStatement sentencia;
    private static ResultSet resultado;

    public static void cargarHorasComboBox(ComboBox<String> comboBox) {

        String[] horas = {
                "9:00", "9:15", "9:30", "9:45", "10:00", "10:15", "10:30", "10:45",
                "11:00", "11:15", "11:30", "11:45", "12:00", "12:15", "12:30", "12:45",
                "13:00", "13:15", "13:30", "13:45", "14:00", "14:15", "14:30", "14:45",
                "16:00", "16:15", "16:30", "16:45", "17:00", "17:15", "17:30", "17:45",
                "18:00", "18:15", "18:30", "18:45", "19:00", "19:15", "19:30", "19:45", "20:00"
        };
        comboBox.getItems().clear();
        comboBox.getItems().addAll(horas);
    }

    public static void cargarDatosTipoInspeccion(ComboBox<TipoInspeccion> comboBox, CONEXIONBD cbd) {
        ObservableList<TipoInspeccion> listaTipoInspeccion = FXCollections.observableArrayList();
        String sql = "SELECT ti.id, ti.Nombre, ti.Precio FROM tipo_inspeccion ti;";

        try {
            conexion = cbd.abrirConexion();
            sentencia = conexion.prepareStatement(sql);
            resultado = sentencia.executeQuery();

            while (resultado.next()) {
                int idTipoInspeccion = resultado.getInt("id");
                String nombreTipoInspeccion = resultado.getString("Nombre");
                Float precioTipoInspeccion = resultado.getFloat("precio");
                TipoInspeccion tipoInspeccion = new TipoInspeccion(idTipoInspeccion, nombreTipoInspeccion, precioTipoInspeccion);
                listaTipoInspeccion.add(tipoInspeccion);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        comboBox.setItems(listaTipoInspeccion);
        comboBox.setConverter(new StringConverter<TipoInspeccion>() {
            @Override
            public String toString(TipoInspeccion tipoInspeccion) {
                return tipoInspeccion.getNombre();
            }
            @Override
            public TipoInspeccion fromString(String string) {
                return null;
            }
        });
    }

    public static void comprobarFechaIsSelected(DatePicker datePicker, ComboBox<String> comboBox) {
        if (datePicker.getValue() == null) {
            comboBox.setDisable(true);
        } else {
            comboBox.setDisable(false);
        }
    }


}

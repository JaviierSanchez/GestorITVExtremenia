package com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaPrincipal;

import com.jorja.proyect.proyectogestoritvfinal.controlador.bbdd.CONEXIONBD;
import com.jorja.proyect.proyectogestoritvfinal.modelo.MarcaVehiculo;
import com.jorja.proyect.proyectogestoritvfinal.modelo.TipoVehiculo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.jorja.proyect.proyectogestoritvfinal.controlador.Utils.cerrarConexion;

public class VentanaPrincipalVehiculoControlador {

    private static Connection conexion;
    private static PreparedStatement sentencia;
    private static ResultSet resultado;


    public static void cargarDatosTipoVehiculo(ComboBox<TipoVehiculo> comboBox, CONEXIONBD cbd) {
        ObservableList<TipoVehiculo> listaTipoVehiculo = FXCollections.observableArrayList();
        String sql = "SELECT t.id, t.Nombre FROM tipo_vehiculo t";
         conexion = cbd.abrirConexion();
         sentencia = null;
         resultado = null;

        try {
            sentencia = conexion.prepareStatement(sql);
            resultado = sentencia.executeQuery();

            while (resultado.next()) {
                int idTipoVehiculo = resultado.getInt("id");
                String nombreTipoVehiculo = resultado.getString("Nombre");
                TipoVehiculo tipoVehiculo = new TipoVehiculo(idTipoVehiculo, nombreTipoVehiculo);
                listaTipoVehiculo.add(tipoVehiculo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            cerrarConexion(cbd);
        }
        // Configurar el ComboBox para mostrar solo el nombre del tipo de vehículo
        comboBox.setItems(listaTipoVehiculo);
        comboBox.setConverter(new StringConverter<TipoVehiculo>() {
            @Override
            public String toString(TipoVehiculo tipoVehiculo) {
                return tipoVehiculo.getNombre();
            }
            @Override
            public TipoVehiculo fromString(String string) {
                return null;
            }
        });
    }
    public static void cargarDatosMarcaVehiculo(ComboBox<MarcaVehiculo> comboBox, CONEXIONBD cbd) {
        ObservableList<MarcaVehiculo> listaMarcaVehiculo = FXCollections.observableArrayList();
        String sql = "SELECT m.id, m.Nombre FROM marcavehiculo m ORDER BY m.Nombre ASC";
        conexion = cbd.abrirConexion();
        try {
            sentencia = conexion.prepareStatement(sql);
            resultado = sentencia.executeQuery();

            while (resultado.next()) {
                int idMarcaVehiculo = resultado.getInt("id");
                String nombreMarcaVehiculo = resultado.getString("Nombre");
                MarcaVehiculo marcaVehiculo = new MarcaVehiculo(idMarcaVehiculo, nombreMarcaVehiculo);
                listaMarcaVehiculo.add(marcaVehiculo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        comboBox.setItems(listaMarcaVehiculo);
        comboBox.setConverter(new StringConverter<MarcaVehiculo>() {
            @Override
            public String toString(MarcaVehiculo marcaVehiculo) {
                return marcaVehiculo.getNombre();
            }

            @Override
            public MarcaVehiculo fromString(String string) {
                return null;
            }
        });

    }
}


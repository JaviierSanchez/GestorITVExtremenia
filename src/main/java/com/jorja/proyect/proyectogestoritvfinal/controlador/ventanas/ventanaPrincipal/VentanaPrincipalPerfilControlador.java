package com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaPrincipal;

import com.jorja.proyect.proyectogestoritvfinal.controlador.bbdd.CONEXIONBD;
import com.jorja.proyect.proyectogestoritvfinal.modelo.HistorialCita;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.jorja.proyect.proyectogestoritvfinal.controlador.Utils.mostrarAlerta;

public class VentanaPrincipalPerfilControlador {

    private static Connection conexion;
    private static PreparedStatement sentencia;
    private static ResultSet resultado;

    public static ObservableList<HistorialCita> addHistorialCita(CONEXIONBD cbd, HistorialCita historialCita) {

        ObservableList<HistorialCita> listaHistorial = FXCollections.observableArrayList();
        String sql = """
                SELECT hi.Fecha as 'Fecha', hi.Hora as 'Hora', hi.id_Vehiculo as 'Matricula', ti.Nombre as 'TipoInspeccion'
			        FROM historial_inspecciones hi
            	        INNER JOIN tipo_inspeccion ti ON ti.ID = hi.Tipo_Inspeccion_id
                    """;
        conexion = cbd.abrirConexion();

        try {
            sentencia = conexion.prepareStatement(sql);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                historialCita = new HistorialCita(
                        resultado.getString("Matricula"),
                        resultado.getString("Fecha"),
                        resultado.getString("Hora"),
                        resultado.getString("TipoInspeccion")
                );
                listaHistorial.add(historialCita);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaHistorial;
    }


    public static void backUpBD(CONEXIONBD cbd) {
        cbd.abrirConexion();
        boolean exito = cbd.hacerCopiaDeSeguridad();
        cbd.cerrarConexion();

        if (exito) {
            mostrarAlerta("Éxito", "La copia de seguridad se realizó correctamente", Alert.AlertType.INFORMATION);
        } else {
            mostrarAlerta("Error", "Hubo un error al realizar la copia de seguridad", Alert.AlertType.ERROR);
        }
    }
}

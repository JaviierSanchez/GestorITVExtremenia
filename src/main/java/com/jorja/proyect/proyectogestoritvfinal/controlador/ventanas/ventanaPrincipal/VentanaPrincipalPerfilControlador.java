package com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaPrincipal;

import com.jorja.proyect.proyectogestoritvfinal.controlador.bbdd.CONEXIONBD;
import com.jorja.proyect.proyectogestoritvfinal.modelo.HistorialCita;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        cbd.hacerCopiaDeSeguridad();
        cbd.cerrarConexion();

    }

    public static void crearInforme(String informeRuta,CONEXIONBD cbd) {
        Connection conexion = null;

        try {
            // Establecer conexión con la base de datos
            conexion = cbd.abrirConexion();
            CONEXIONBD.comprobarConexion(conexion);

            // Cargar el archivo del informe
            JasperReport jasperReport = JasperCompileManager.compileReport(VentanaPrincipalPerfilControlador.class.getResourceAsStream(informeRuta));

            // Llenar el informe con datos de la base de datos
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conexion);

            // Mostrar el informe
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cbd.cerrarConexion();
        }
    }
}

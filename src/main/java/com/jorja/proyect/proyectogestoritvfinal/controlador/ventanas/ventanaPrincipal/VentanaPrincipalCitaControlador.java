package com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaPrincipal;

import com.jorja.proyect.proyectogestoritvfinal.controlador.bbdd.CONEXIONBD;
import com.jorja.proyect.proyectogestoritvfinal.modelo.Cita;
import com.jorja.proyect.proyectogestoritvfinal.modelo.TipoInspeccion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;

import static com.jorja.proyect.proyectogestoritvfinal.controlador.Utils.cerrarConexion;

public class VentanaPrincipalCitaControlador {

    private static Connection conexion;
    private static PreparedStatement sentencia;
    private static ResultSet resultado;

    public static ObservableList<Cita> obtenerListaCitaBD(CONEXIONBD cbd, Cita cita) {

        ObservableList<Cita> listaCita = FXCollections.observableArrayList();
        String sql = """
                SELECT C.id AS Id,C.id_Vehiculo AS Matricula, C.Fecha AS Fecha,C.Hora AS Hora,
                TV.Nombre AS TipoVehiculo, TI.Nombre AS TipoInspeccion, TI.Precio as Precio, C.Activa AS Activa
                      FROM CITA C
                           INNER JOIN TIPO_VEHICULO TV ON TV.id = C.Tipo_Vehiculo_id
                           INNER JOIN TIPO_INSPECCION TI ON TI.id = C.Tipo_Inspeccion_id
                    """;

        try {
            conexion = cbd.abrirConexion();
            sentencia = conexion.prepareStatement(sql);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                cita = new Cita(
                        resultado.getInt("Id"),
                        resultado.getString("Matricula"),
                        resultado.getString("Fecha"),
                        resultado.getString("Hora"),
                        resultado.getString("TipoVehiculo"),
                        resultado.getString("TipoInspeccion"),
                        resultado.getString("Precio"),
                        resultado.getBoolean("Activa"));
                listaCita.add(cita);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            cerrarConexion(cbd);
        }
        return listaCita;
    }

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
        // Comprobar que el datepicker tiene valor
        if (datePicker.getValue() == null) {
            comboBox.setDisable(true);
        } else {
            comboBox.setDisable(false);
        }
    }

    public static void deshabilitarFinDeSemana(DatePicker datePicker) {
        // Definimos un Callback que será usado para personalizar las celdas del DatePicker
        Callback<DatePicker, DateCell> dayCellFactory = new Callback<>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                // Retornamos una nueva DateCell para cada día en el DatePicker
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        // Llamamos al método de la superclase para actualizar el item
                        super.updateItem(item, empty);
                        // Deshabilitamos todos los sábados y domingos
                        if (item != null && (item.getDayOfWeek() == DayOfWeek.SATURDAY || item.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                            setDisable(true); // Deshabilitamos la celda
                            setStyle("-fx-background-color: #ffc0cb;"); // Cambiamos el fondo de la celda a color rosa
                        }
                    }
                };
            }
        };

        // Establecemos el dayCellFactory personalizado en el DatePicker
        datePicker.setDayCellFactory(dayCellFactory);
    }



}

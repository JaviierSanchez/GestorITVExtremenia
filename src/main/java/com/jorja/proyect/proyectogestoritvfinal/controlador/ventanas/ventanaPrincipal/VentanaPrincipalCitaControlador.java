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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.jorja.proyect.proyectogestoritvfinal.controlador.Utils.cerrarConexion;

public class VentanaPrincipalCitaControlador {

    private static Connection conexion;
    private static PreparedStatement sentencia;
    private static ResultSet resultado;

    /***
     * Metodo para mostrar los datos en el TableView
     * @param cbd
     * @param cita
     * @return
     */
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
                        resultado.getString("TipoInspeccion"),
                        resultado.getString("TipoVehiculo"),
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

    public static List<String> obtenerHorasOcupadas2(LocalDate fecha, CONEXIONBD cbd) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser null");
        }

        List<String> horasOcupadas = new ArrayList<>();
        List<String> horasDisponibles = new ArrayList<>();
        String[] totalHoras = {
                "09:00:00", "09:15:00", "09:30:00", "09:45:00", "10:00:00", "10:15:00", "10:30:00", "10:45:00",
                "11:00:00", "11:15:00", "11:30:00", "11:45:00", "12:00:00", "12:15:00", "12:30:00", "12:45:00",
                "13:00:00", "13:15:00", "13:30:00", "13:45:00", "14:00:00", "14:15:00", "14:30:00", "14:45:00",
                "16:00:00", "16:15:00", "16:30:00", "16:45:00", "17:00:00", "17:15:00", "17:30:00", "17:45:00",
                "18:00:00", "18:15:00", "18:30:00", "18:45:00", "19:00:00", "19:15:00", "19:30:00", "19:45:00",
                "20:00:00"
        };

        Connection conexion = cbd.abrirConexion();
        String sql = "SELECT c.Hora FROM cita c WHERE c.Fecha = ?";
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, String.valueOf(fecha));
            resultado = sentencia.executeQuery();

            while (resultado.next()) {
                horasOcupadas.add(resultado.getString("Hora"));
            }

            LocalTime ahora = LocalTime.now();
            for (String hora : totalHoras) {
                LocalTime horaActual = LocalTime.parse(hora);
                if (!horasOcupadas.contains(hora) && (!fecha.equals(LocalDate.now()) || (fecha.equals(LocalDate.now()) && horaActual.isAfter(ahora)))) {
                    horasDisponibles.add(hora);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            cerrarConexion(cbd);
        }
        return horasDisponibles;
    }


    public static void cargarHorasComboBox(ComboBox<String> comboBox, List<String> horasDisponibles) {
        comboBox.getItems().clear();
        for (String hora : horasDisponibles) {
            comboBox.getItems().add(hora);
        }
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
        } finally {
            cerrarConexion(cbd);
        }

        comboBox.setItems(listaTipoInspeccion);
        comboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(TipoInspeccion tipoInspeccion) {
                return tipoInspeccion.getNombre();
            }

            @Override
            public TipoInspeccion fromString(String string) {
                return null;  // No se necesita implementación para este método
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

    public static void deshabilitarDiasNoValidos(DatePicker datePicker) {
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
                        // Obtenemos la fecha actual
                        LocalDate today = LocalDate.now();
                        // Deshabilitamos todos los sábados, domingos y los días anteriores a hoy
                        if (item != null && (item.isBefore(today) || item.getDayOfWeek() == DayOfWeek.SATURDAY || item.getDayOfWeek() == DayOfWeek.SUNDAY)) {
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

    // Método para verificar si el ID de la cita existe en la base de datos
    public static boolean existeCita(int idCita) {
        String sql = "SELECT id FROM cita WHERE id = ?";
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setInt(1, idCita);
            resultado = sentencia.executeQuery();
            return resultado.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para obtener el ID del tipo de inspeccion seleccionado
    public static int obtenerIdTipoInspeccion(TipoInspeccion tipoInspeccionSeleccionado) {
        int idTipoInspeccion = 0;
        String sql = "SELECT id FROM tipo_inspeccion WHERE Nombre = ?";
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, tipoInspeccionSeleccionado.getNombre());
            resultado = sentencia.executeQuery();
            if (resultado.next()) {
                idTipoInspeccion = resultado.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idTipoInspeccion;
    }

    public static void eliminarCitasPasadas(CONEXIONBD cbd) {
        String sql = "DELETE FROM cita WHERE Fecha < ?";
        LocalDate hoy = LocalDate.now();
        Connection conexion = cbd.abrirConexion();

        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, String.valueOf(hoy));
            int filas = sentencia.executeUpdate();

            if (filas > 0) {
                System.out.println("Citas pasadas eliminadas: " + filas);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            cbd.cerrarConexion();
        }

    }
}

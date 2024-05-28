package com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaPrincipal;

import com.jorja.proyect.proyectogestoritvfinal.controlador.bbdd.CONEXIONBD;
import com.jorja.proyect.proyectogestoritvfinal.modelo.MarcaVehiculo;
import com.jorja.proyect.proyectogestoritvfinal.modelo.TipoVehiculo;
import com.jorja.proyect.proyectogestoritvfinal.modelo.Vehiculo;
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


    /***
     * Metodo para sacar todos los datos que se quieren mostrar en el tableview
     * @param cbd
     * @param vehiculo
     * @return
     */
    public static ObservableList<Vehiculo> addVehiculo(CONEXIONBD cbd, Vehiculo vehiculo) {

        ObservableList<Vehiculo> listaVehiculo = FXCollections.observableArrayList();
        String sql = """
                SELECT v.Matricula as Matricula,m.Nombre as marca,
                                    v.Modelo as Modelo ,v.Año as Año,du.Correo as correoUsuario,tv.Nombre as tipoVehiculo
                                    FROM vehiculo v
                                                INNER JOIN datos_usuario du ON du.id = v.Usuario_id
                                                INNER JOIN tipo_vehiculo tv ON tv.id = v.Tipo_Vehiculo_id
                                                INNER JOIN marcavehiculo m on m.id = v.Marca_id
                    """;
        conexion = cbd.abrirConexion();

        try {
            sentencia = conexion.prepareStatement(sql);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                vehiculo = new Vehiculo(
                        resultado.getString("Matricula"),
                        resultado.getString("marca"),
                        resultado.getString("Modelo"),
                        resultado.getString("Año"),
                        resultado.getString("correoUsuario"),
                        resultado.getString("tipoVehiculo")
                );
                listaVehiculo.add(vehiculo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaVehiculo;
    }


    /***
     * Metodo para cargar los nombres de los tipos de vehiculo que hay
     * @param comboBox
     * @param cbd
     */
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
        // Convertimos el objeto de tipoVehiculo a toString
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

    /***
     * Metodo para cargar el nombre de marcas que hay en el combobox
     * @param comboBox
     * @param cbd
     */
    public static void cargarDatosMarcaVehiculo(ComboBox<MarcaVehiculo> comboBox, CONEXIONBD cbd) {
        ObservableList<MarcaVehiculo> listaMarcaVehiculo = FXCollections.observableArrayList();
        String sql = "SELECT m.id, UPPER(m.Nombre) as Nombre FROM marcavehiculo m ORDER BY m.Nombre ASC";
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
        // Convertir el objetos marcas a toString
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

    /***
     * Método para obtener el ID de la marca seleccionada
     * @param marcaSeleccionada
     * @return
     */
    public static int obtenerIdMarca(MarcaVehiculo marcaSeleccionada) {
        int idMarca = 0;
        String sql = "SELECT id FROM marcavehiculo WHERE Nombre = ?";
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, marcaSeleccionada.getNombre());
            resultado = sentencia.executeQuery();
            if (resultado.next()) {
                idMarca = resultado.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idMarca;
    }

    /***
     * Método para obtener el ID del tipo de vehículo seleccionado
     * @param tipoVehiculoSeleccionado
     * @return
     */
    public static int obtenerIdTipoVehiculo(TipoVehiculo tipoVehiculoSeleccionado) {
        int idTipoVehiculo = 0;
        String sql = "SELECT id FROM tipo_vehiculo WHERE Nombre = ?";
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, tipoVehiculoSeleccionado.getNombre());
            resultado = sentencia.executeQuery();
            if (resultado.next()) {
                idTipoVehiculo = resultado.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idTipoVehiculo;
    }


}


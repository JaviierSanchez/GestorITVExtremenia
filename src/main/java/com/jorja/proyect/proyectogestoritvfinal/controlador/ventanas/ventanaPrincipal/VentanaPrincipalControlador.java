package com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaPrincipal;

import com.jorja.proyect.proyectogestoritvfinal.controlador.bbdd.CONEXIONBD;
import com.jorja.proyect.proyectogestoritvfinal.modelo.Sesion;
import com.jorja.proyect.proyectogestoritvfinal.modelo.Usuario;
import com.jorja.proyect.proyectogestoritvfinal.vista.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.jorja.proyect.proyectogestoritvfinal.controlador.Utils.*;

public class VentanaPrincipalControlador implements Initializable {

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;
    private CONEXIONBD cbd;
    private Date fecha;
    private Usuario usuario;

    public VentanaPrincipalControlador() {
        cbd = new CONEXIONBD();
    }

    @FXML
    private Button btnLateralCita;

    @FXML
    private Button btnLateralInicio;

    @FXML
    private Button btnLateralPerfil;

    @FXML
    private Button btnLateralUsuarios;

    @FXML
    private Button btnLateralVehiculo;

    @FXML
    private TableColumn<?, ?> columnMatriculaCita;
    @FXML
    private TableColumn<?, ?> columnPrecioCita;
    @FXML
    private TableColumn<?, ?> columnTipoInspeccionCita;

    @FXML
    private TableColumn<?, ?> columnTipoVehiculoCita;

    @FXML
    private TableColumn<?, ?> columnFechaCita;

    @FXML
    private TableColumn<?, ?> columnHoraCita;

    @FXML
    private TableView<Usuario> TableViewUsuario;
    @FXML
    private TableColumn<Usuario, String> columnIdUsuario;
    @FXML
    private TableColumn<Usuario, String> columnNombreUsuario;
    @FXML
    private TableColumn<Usuario, String> columnApellidoUsuario;
    @FXML
    private TableColumn<Usuario, String> columnTelefonoUsuario;
    @FXML
    private TableColumn<Usuario, String> columnCorreoUsuario;
    @FXML
    private TableColumn<Usuario, String> columnPasswordUsuario;

    @FXML
    private TableColumn<Usuario, String> columnFechaAltaUsuario;
    @FXML
    private TableColumn<Usuario, String> columnAdminUsuario;

    private ObservableList<Usuario> addUsuarioLista;

    @FXML
    private TableColumn<?, ?> columnAñoVehiculo;

    @FXML
    private TableColumn<?, ?> columnMarcaVehiculo;


    @FXML
    private TableColumn<?, ?> columnMatriculaVehiculo;
    @FXML
    private TableColumn<?, ?> columTipoVehiculoVehiculo;
    @FXML
    private TableColumn<?, ?> columnModeloVehiculo;


    @FXML
    private BarChart<?, ?> graficoUsuarios;

    @FXML
    private AnchorPane layoutInicio;

    @FXML
    private BorderPane layoutPadre;

    @FXML
    private AnchorPane layoutPedirCita;

    @FXML
    private AnchorPane layoutPerfil;

    @FXML
    private AnchorPane layoutUsuarios;

    @FXML
    private AnchorPane layoutVehiculo;

    @FXML
    private Label lblCountDate;

    @FXML
    private Label lblCountMoney;

    @FXML
    private Label lblCountUser;

    @FXML
    private Label lblCountvehicle;

    @FXML
    private Label lblNombreUsuario;

    @FXML
    private TextField txtApellidoPerfil;

    @FXML
    private TextField txtApellidoUsuario;

    @FXML
    private TextField txtAñoVehiculo;

    @FXML
    private TextField txtBusquedaUsuario;

    @FXML
    private TextField txtBusquedaVehiculo;

    @FXML
    private TextField txtCorreoPerfil;

    @FXML
    private TextField txtCorreoUsuario;

    @FXML
    private DatePicker txtFechaCita;

    @FXML
    private ComboBox<?> txtHoraCita;

    @FXML
    private TextField txtIdUsuario;

    @FXML
    private TextField txtMarcaVehiculo;

    @FXML
    private TextField txtMatriculaCita;

    @FXML
    private TextField txtMatriculaVehicula;

    @FXML
    private TextField txtModeloVehiculo;

    @FXML
    private TextField txtNombrePerfil;

    @FXML
    private TextField txtNombreUsuario;
    @FXML
    private PasswordField txtPassWordUsuario;

    @FXML
    private TextField txtPrecioCita;

    @FXML
    private TextField txtTelefonoPerfil;

    @FXML
    private TextField txtTelefonoUsuario;

    @FXML
    private ComboBox<?> txtTipoInspeccionCita;

    @FXML
    private ComboBox<?> txtTipoVehiculoCita;

    @FXML
    private ComboBox<?> txtTipoVehiculoVehiculo;

    @FXML
    void btnAddCita(ActionEvent event) {

    }


    @FXML
    void btnAddVehiculo(ActionEvent event) {

    }

    @FXML
    void btnCleanCita(ActionEvent event) {

    }


    @FXML
    void btnCleanVehiculo(ActionEvent event) {

    }

    @FXML
    void btnDeleteCita(ActionEvent event) {

    }


    @FXML
    void btnDeleteVehiculo(ActionEvent event) {

    }

    @FXML
    void btnModificarPasswordPerfil(ActionEvent event) {

    }

    @FXML
    void btnUpdateCita(ActionEvent event) {

    }


    @FXML
    void btnUpdateVehiculo(ActionEvent event) {

    }

    @FXML
    void btnMinimizar(ActionEvent event) {
        Stage stage = (Stage) layoutPadre.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void btnSalir(ActionEvent event) {
        System.exit(0);
    }

    public void cambiarVentana(ActionEvent actionEvent) {

        Button botonPresionado = (Button) actionEvent.getSource();
        Button[] botones = {btnLateralInicio, btnLateralUsuarios, btnLateralCita, btnLateralVehiculo, btnLateralPerfil};
        Pane[] layouts = {layoutInicio, layoutUsuarios, layoutPedirCita, layoutVehiculo, layoutPerfil};
        VentanaPrincipalInicioControlador.cambiarVentana(botonPresionado, botones, layouts);

        contadorTotalUsuarios();
        contadorTotalCitas();
        contardorTotalVehiculos();
        contadorTotalGanaciasMensuales();
        cargarDatosGraficoUsuario();
    }

    public void cerrarVentana(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cerrar Sesión");
        alert.setHeaderText("¿Estás seguro de que deseas cerrar sesión?");
        Optional<ButtonType> result = alert.showAndWait();

        // Verificar la respuesta del usuario
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Cerrar la ventana actual
            Node source = (Node) actionEvent.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

            Main main = new Main();

            try {
                main.start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    //Logica Ventana Principal Inicio

    /***
     * Metodos para el contador de la pantalla Inicio
     * Se crea crea la sql y se pasa a los metodos las consultas y las
     * etiquetas que se quiere modificar
     */
    public void contadorTotalUsuarios() {
        String sqlUsuarios = "SELECT COUNT(du.ID) as total FROM datos_usuario du";
        VentanaPrincipalInicioControlador.contadorTarjetas(sqlUsuarios, lblCountUser, cbd);
    }

    public void contardorTotalVehiculos() {
        String sqlVehiculos = "SELECT COUNT(v.matricula) as total From vehiculo v";
        VentanaPrincipalInicioControlador.contadorTarjetas(sqlVehiculos, lblCountvehicle, cbd);
    }

    public void contadorTotalCitas() {
        String sqlCitas = "SELECT COUNT(c.id) as total FROM cita C";

        VentanaPrincipalInicioControlador.contadorTarjetas(sqlCitas, lblCountDate, cbd);
    }

    public void contadorTotalGanaciasMensuales() {
        String sqlGanancias = """
                SELECT
                    SUM(ti.Precio) AS total
                FROM
                    cita c
                        INNER JOIN
                    tipo_inspeccion ti ON c.Tipo_Inspeccion_id = ti.id
                WHERE
                    YEAR(c.Fecha) = YEAR(CURDATE()) AND MONTH(c.Fecha) = MONTH(CURDATE());
                                
                """;


        VentanaPrincipalInicioControlador.contadorTarjetas(sqlGanancias, lblCountMoney, cbd);
    }

    public void cargarDatosGraficoUsuario() {

        graficoUsuarios.getData().clear();

        String sqlGrafico = """
                SELECT
                     MONTH(FechaAlta) AS Mes,
                     COUNT(id) AS TotalUsuariosPorMes
                         FROM datos_usuario
                             GROUP BY MONTH(FechaAlta)
                                 ORDER BY Mes
                """;


        conexion = cbd.abrirConexion();

        XYChart.Series chart = new XYChart.Series();
        try {
            sentencia = conexion.prepareStatement(sqlGrafico);
            resultado = sentencia.executeQuery();

            while (resultado.next()) {
                int mesNumero = resultado.getInt(1);
                String nombreMes = obtenerNombreMes(mesNumero); // Obtener el nombre del mes
                chart.getData().add(new XYChart.Data(nombreMes, resultado.getInt(2)));
            }

            graficoUsuarios.getData().add(chart);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            try {
                cbd.cerrarConexion();
                conexion.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Método para obtener el nombre del mes a partir del número del mes
    private String obtenerNombreMes(int numeroMes) {
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] nombresMeses = dfs.getMonths();
        return nombresMeses[numeroMes - 1];
    }


    // Logica Ventana Principal Usuario

    @FXML
    void btnAddUsuario(ActionEvent event) {

        Date fecha = new Date();
        Date fechaActual = new Date(fecha.getTime());

        String sql = """
                INSERT INTO datos_usuario
                 (Nombre, Apellido,Telefono, Correo, Contraseña, FechaAlta)
                  VALUES (?, ?, ?, ?, ?, ?)
                """;
        conexion = cbd.abrirConexion();

        // Comprobaciones de campos, si esta vacio muestra alerta error, sino compprueba que los datos se han introducido con formato correcto
        if (txtNombreUsuario.getText().isEmpty() || txtApellidoUsuario.getText().isEmpty() || txtTelefonoUsuario.getText().isEmpty() ||
                txtCorreoUsuario.getText().isEmpty() || txtPassWordUsuario.getText().isEmpty()) {
            mostrarAlerta("Error", "Rellena los campos", Alert.AlertType.ERROR);
        } else {

            if (!txtTelefonoUsuario.getText().matches(TELEFONOREGEX)) {
                mostrarAlerta("Error", "Teléfono no válido. Debe tener 9 dígitos. \n Ejemplo: 123456789", Alert.AlertType.ERROR);
                return;
            }
            if (!txtCorreoUsuario.getText().matches(CORREOREGEX)) {
                mostrarAlerta("Error", "El correo electrónico ingresado no es válido.", Alert.AlertType.ERROR);
                return;
            }
            if (!txtPassWordUsuario.getText().matches(PASSWORDREGEX)) {
                mostrarAlerta("Error", "La contraseña debe tener al menos 8 caracteres y contener al menos una letra y un número.", Alert.AlertType.ERROR);
                return;
            }
            // Comprobar que el usuario no se encuentra en la BBDD
            String comprobarUsuariosql = "SELECT du.Correo FROM datos_usuario du where du.Correo = ?";
            try {
                sentencia = conexion.prepareStatement(comprobarUsuariosql);
                sentencia.setString(1, txtCorreoUsuario.getText());
                resultado = sentencia.executeQuery();

                if (resultado.next()) {
                    mostrarAlerta("Error", "El usuario ya existe", Alert.AlertType.ERROR);
                } else {
                    String fechaActual2 = new SimpleDateFormat("yyyy-MM-dd").format(fechaActual);

                    sentencia = conexion.prepareStatement(sql);
                    sentencia.setString(1, txtNombreUsuario.getText());
                    sentencia.setString(2, txtApellidoUsuario.getText());
                    sentencia.setString(3, txtTelefonoUsuario.getText());
                    sentencia.setString(4, txtCorreoUsuario.getText());
                    sentencia.setString(5, txtPassWordUsuario.getText());
                    sentencia.setString(6, String.valueOf(fechaActual2));
                    sentencia.executeUpdate();
                    mostrarAlerta("Añadido", "El usuario ha sido añadido", Alert.AlertType.INFORMATION);
                    btnCleanUsuarios(event);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    cbd.cerrarConexion();
                    conexion.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @FXML
    void btnUpdateUsuario(ActionEvent event) {

    }

    @FXML
    void btnDeleteUsuario(ActionEvent event) {

        String sql = "DELETE from datos_usuario WHERE Correo = ?";
        conexion = cbd.abrirConexion();

        if (txtNombreUsuario.getText().isEmpty() || txtApellidoUsuario.getText().isEmpty() || txtTelefonoUsuario.getText().isEmpty() ||
                txtCorreoUsuario.getText().isEmpty() || txtPassWordUsuario.getText().isEmpty()) {
            mostrarAlerta("Error", "Rellena los campos", Alert.AlertType.ERROR);
        } else {
            Alert alertaEliminar = new Alert(Alert.AlertType.CONFIRMATION);
            alertaEliminar.setTitle("Confirmar eliminación");
            alertaEliminar.setContentText("¿Estás seguro que quieres eliminar al usuario?");
            Optional<ButtonType> opcion = alertaEliminar.showAndWait();

            if (opcion.get() == ButtonType.OK) {
                try {
                    sentencia = conexion.prepareStatement(sql);
                    sentencia.setString(1, txtCorreoUsuario.getText());
                    sentencia.executeUpdate();
                    mostrarAlerta("Eliminado con éxito", "El empleado ha sido eliminado con éxito", Alert.AlertType.INFORMATION);
                    btnCleanUsuarios(event);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @FXML
    public void btnCleanUsuarios(ActionEvent actionEvent) {
        txtIdUsuario.setText("");
        txtNombreUsuario.setText("");
        txtApellidoUsuario.setText("");
        txtTelefonoUsuario.setText("");
        txtCorreoUsuario.setText("");
        txtPassWordUsuario.setText("");
    }

    public ObservableList<Usuario> addUsuario() {

        ObservableList<Usuario> listaUsuario = FXCollections.observableArrayList();
        String sql = "SELECT * FROM datos_usuario";
        conexion = cbd.abrirConexion();
        try {
            sentencia = conexion.prepareStatement(sql);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                usuario = new Usuario(
                        resultado.getInt("id"),
                        resultado.getString("Nombre"),
                        resultado.getString("Apellido"),
                        resultado.getString("Telefono"),
                        resultado.getString("Correo"),
                        resultado.getString("Contraseña"),
                        resultado.getBoolean("Administrador"),
                        resultado.getString("FechaAlta"));
                listaUsuario.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuario;
    }

    public void addUsuarioLista() {
        addUsuarioLista = addUsuario();
        columnIdUsuario.setCellValueFactory(new PropertyValueFactory<>(COLUMNIDUSUARIO));
        columnNombreUsuario.setCellValueFactory(new PropertyValueFactory<>(COLUMNNOMBREUSUARIO));
        columnApellidoUsuario.setCellValueFactory(new PropertyValueFactory<>(COLUMNAPELLIDOUSUARIO));
        columnTelefonoUsuario.setCellValueFactory(new PropertyValueFactory<>(COLUMNTELEFONOUSUARIO));
        columnCorreoUsuario.setCellValueFactory(new PropertyValueFactory<>(COLUMNCORREOUSUARIO));
        columnPasswordUsuario.setCellValueFactory(new PropertyValueFactory<>(COLUMNCONTRASEÑAUSUARIO));
        columnAdminUsuario.setCellValueFactory(new PropertyValueFactory<>(COLUMNADMINISTRADORUSUARIO));
        columnFechaAltaUsuario.setCellValueFactory(new PropertyValueFactory<>(COLUMNFECHAALTAUSUARIO));
        TableViewUsuario.setItems(addUsuarioLista);
    }

    public void mostrarUsuarioSeleccionado() {
        try {
            Usuario usuario = TableViewUsuario.getSelectionModel().getSelectedItem();
            int indice = TableViewUsuario.getSelectionModel().getSelectedIndex();

            if (indice < 0) {
                return;
            }
            txtIdUsuario.setText(String.valueOf(usuario.getId()));
            txtNombreUsuario.setText(usuario.getNombre());
            txtApellidoUsuario.setText(usuario.getApellido());
            txtTelefonoUsuario.setText(usuario.getTelefono());
            txtCorreoUsuario.setText(usuario.getCorreo());
            txtPassWordUsuario.setText(usuario.getContraseña());
        } catch (NullPointerException e) {
            System.err.println("ADVERTENCIA: No se ha seleccionado ningún empleado.");
            }
    }


    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipoAlerta) {
        Alert alert = new Alert(tipoAlerta);
        alert.setHeaderText(null);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Tarjetas Contador
        contadorTotalUsuarios();
        contadorTotalCitas();
        contardorTotalVehiculos();
        contadorTotalGanaciasMensuales();
        cargarDatosGraficoUsuario();
        addUsuarioLista();
        mostrarUsuarioSeleccionado();

        // Sacar nombre usuario que ha iniciado sesion
        Usuario usuarioActual = Sesion.getUsuarioActual();
        if (usuarioActual != null) {
            lblNombreUsuario.setText(usuarioActual.getNombre());
        } else {
            lblNombreUsuario.setText("Usuario desconodido");
        }

    }


}

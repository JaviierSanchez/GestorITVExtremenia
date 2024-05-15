package com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaPrincipal;

import com.jorja.proyect.proyectogestoritvfinal.controlador.Utils;
import com.jorja.proyect.proyectogestoritvfinal.controlador.bbdd.CONEXIONBD;
import com.jorja.proyect.proyectogestoritvfinal.modelo.*;
import com.jorja.proyect.proyectogestoritvfinal.vista.Main;
import com.jorja.proyect.proyectogestoritvfinal.vista.VentanaCambiarPassword;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.jorja.proyect.proyectogestoritvfinal.controlador.Utils.*;
import static com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaPrincipal.VentanaPrincipalCitaControlador.*;
import static com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaPrincipal.VentanaPrincipalUsuarioControlador.sacarNombreUsuarioLogueado;
import static com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaPrincipal.VentanaPrincipalVehiculoControlador.cargarDatosMarcaVehiculo;
import static com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaPrincipal.VentanaPrincipalVehiculoControlador.cargarDatosTipoVehiculo;

public class VentanaPrincipalControlador implements Initializable {

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;
    private CONEXIONBD cbd;
    private Date fecha;
    private Usuario usuario;
    private Vehiculo vehiculo;
    private Cita cita;

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
    private TableView<Cita> TableViewCita;
    private ObservableList<Cita> addCitaLista;
    @FXML
    private TableColumn<?, ?> columnIdCita;
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
    private TableColumn<?, ?> columnActivaCita;

    private ObservableList<Usuario> addUsuarioLista;
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

    // Elementos Vehiculo
    private ObservableList<Vehiculo> addVehiculoLista;
    @FXML
    private TableView<Vehiculo> TableViewVehiculo;
    @FXML
    private TableColumn<Vehiculo, String> columnMatriculaVehiculo;
    @FXML
    private TableColumn<Vehiculo, String> columnMarcaVehiculo;
    @FXML
    private TableColumn<Vehiculo, String> columnModeloVehiculo;
    @FXML
    private TableColumn<Vehiculo, String> columnAñoVehiculo;
    @FXML
    private TableColumn<Vehiculo, String> columTipoVehiculoVehiculo;
    @FXML
    private TableColumn<Vehiculo, String> columPropietarioVehiculo;
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
    private TextField txtAdminUsuario;

    @FXML
    private TextField txtIdUsuarioVehiculo;

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
    private ComboBox<String> txtHoraCita;

    @FXML
    private TextField txtIdUsuario;

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
    private ComboBox<MarcaVehiculo> txtMarcaVehiculo;

    @FXML
    private ComboBox<TipoInspeccion> txtTipoInspeccionCita;

    @FXML
    private ComboBox<TipoVehiculo> txtTipoVehiculoCita;

    @FXML
    private ComboBox<TipoVehiculo> txtTipoVehiculoVehiculo;


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
        buscarUsuarioTableView();
        buscarVehiculoTableView();
        asignarDatosUsuarioSesion();
        limpiarCamposBusqueda();
        btnCleanUsuarios(actionEvent);
        btnCleanVehiculo(actionEvent);
        btnCleanCita(actionEvent);
    }

    // Metodo para limpiar la barra de busqueda cuando cambias de ventana
    private void limpiarCamposBusqueda() {
        txtBusquedaUsuario.clear();
        txtBusquedaVehiculo.clear();
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
            cerrarConexion(cbd);
        }
    }

    // Logica Ventana Principal Cita
    @FXML
    void btnAddCita(ActionEvent event) {

    }

    @FXML
    void btnCleanCita(ActionEvent event) {

    }

    @FXML
    void btnDeleteCita(ActionEvent event) {

    }

    @FXML
    void btnUpdateCita(ActionEvent event) {

    }

    public ObservableList<Cita> obtenerListaCitaBD() {

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


    public void agregarCitaLista() {
        addCitaLista = obtenerListaCitaBD();
        columnIdCita.setCellValueFactory(new PropertyValueFactory<>(COLUMNIDCITA));
        columnMatriculaCita.setCellValueFactory(new PropertyValueFactory<>(COLUMNMATRICULAVEHICULOCITA));
        columnFechaCita.setCellValueFactory(new PropertyValueFactory<>(COLUMNFECHACITA));
        columnHoraCita.setCellValueFactory(new PropertyValueFactory<>(COLUMNHORACITA));
        columnTipoVehiculoCita.setCellValueFactory(new PropertyValueFactory<>(COLUMNTIPOVEHICULOIDCITA));
        columnTipoInspeccionCita.setCellValueFactory(new PropertyValueFactory<>(COLUMNTIPOINSPECCIONIDCITA));
        columnPrecioCita.setCellValueFactory(new PropertyValueFactory<>(COLUMNPRECIOCITA));
        columnActivaCita.setCellValueFactory(new PropertyValueFactory<>(COLUMNACTIVACITA));
        TableViewCita.setItems(addCitaLista);
    }

    //Cargar horas en el combobox

    public void cargarHorasCitas() {
        cargarHorasComboBox(txtHoraCita);
    }



    // Logica Ventana Principal Usuario

    @FXML
    void btnAddUsuario(ActionEvent event) {

        String fechaAlta = obtenerFechaActual();

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

            if (!validarTelefono(txtTelefonoUsuario) || !validarCorreo(txtCorreoUsuario) || !validarPassword(txtPassWordUsuario))
                return;

            // Comprobar que el usuario no se encuentra en la BBDD
            String comprobarUsuariosql = "SELECT du.Correo FROM datos_usuario du where du.Correo = ?";
            try {
                sentencia = conexion.prepareStatement(comprobarUsuariosql);
                sentencia.setString(1, txtCorreoUsuario.getText());
                resultado = sentencia.executeQuery();

                if (resultado.next()) {
                    mostrarAlerta("Error", "El usuario ya existe", Alert.AlertType.ERROR);
                } else {
                    String hashedPassword = Utils.hashPassword(txtPassWordUsuario.getText());

                    sentencia = conexion.prepareStatement(sql);
                    sentencia.setString(1, txtNombreUsuario.getText());
                    sentencia.setString(2, txtApellidoUsuario.getText());
                    sentencia.setString(3, txtTelefonoUsuario.getText());
                    sentencia.setString(4, txtCorreoUsuario.getText());
                    sentencia.setString(5, hashedPassword);
                    sentencia.setString(6, fechaAlta);
                    sentencia.executeUpdate();
                    mostrarAlerta("Añadido", "El usuario ha sido añadido", Alert.AlertType.INFORMATION);
                    btnCleanUsuarios(event);
                    agregarUsuarioLista();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                cerrarConexion(cbd);
            }
        }
    }

    @FXML
    void btnUpdateUsuario(ActionEvent event) {

        String sql = "UPDATE datos_usuario du SET du.Nombre = ?, du.Apellido = ?, du.Telefono = ?, du.Correo = ?, du.Contraseña = ?, du.Administrador = ?, du.FechaAlta = ? WHERE du.id = ?";

        conexion = cbd.abrirConexion();

        // Comprobaciones de campos, si esta vacio muestra alerta error, sino compprueba que los datos se han introducido con formato correcto
        if (txtNombreUsuario.getText().isEmpty() || txtApellidoUsuario.getText().isEmpty() || txtTelefonoUsuario.getText().isEmpty() ||
                txtCorreoUsuario.getText().isEmpty() || txtPassWordUsuario.getText().isEmpty()) {
            mostrarAlerta("Error", "Rellena los campos", Alert.AlertType.ERROR);
        } else {

            if (!validarTelefono(txtTelefonoUsuario) || !validarCorreo(txtCorreoUsuario) || !validarPassword(txtPassWordUsuario))
                return;

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Actualizar empleado");
            alert.setContentText("¿Estás seguro que quieres actualizar los cambios?");

            ButtonType opcion = alert.showAndWait().orElse(ButtonType.CANCEL);

            if (opcion == ButtonType.OK) {
                try {
                    String hashedPassword = hashPassword(txtPassWordUsuario.getText());
                    sentencia = conexion.prepareStatement(sql);
                    sentencia.setString(1, txtNombreUsuario.getText());
                    sentencia.setString(2, txtApellidoUsuario.getText());
                    sentencia.setString(3, txtTelefonoUsuario.getText());
                    sentencia.setString(4, txtCorreoUsuario.getText());
                    sentencia.setString(5, hashedPassword);
                    sentencia.setInt(6, (txtAdminUsuario.getText().equalsIgnoreCase("true")) ? 1 : 0);
                    sentencia.setString(7, usuario.getFechaAlta());
                    sentencia.setString(8, txtIdUsuario.getText());
                    sentencia.executeUpdate();

                    mostrarAlerta("Usuario actualizado", "El usuario ha sido actualizado", Alert.AlertType.INFORMATION);
                    agregarUsuarioLista();
                    btnCleanUsuarios(event);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    cerrarConexion(cbd);
                }
            }

        }
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
                    agregarUsuarioLista();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    cerrarConexion(cbd);
                }
            }
        }
    }

    @FXML
    public void btnCleanUsuarios(ActionEvent actionEvent) {
        txtIdUsuario.clear();
        txtNombreUsuario.clear();
        txtApellidoUsuario.clear();
        txtTelefonoUsuario.clear();
        txtCorreoUsuario.clear();
        txtPassWordUsuario.clear();
        txtAdminUsuario.clear();
    }

    public ObservableList<Usuario> addUsuario() {

        ObservableList<Usuario> listaUsuario = FXCollections.observableArrayList();
        String sql = "SELECT * FROM datos_usuario ORDER BY id";
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

    public void agregarUsuarioLista() {
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

            if (indice < 0) return;

            txtIdUsuario.setText(String.valueOf(usuario.getId()));
            txtNombreUsuario.setText(usuario.getNombre());
            txtApellidoUsuario.setText(usuario.getApellido());
            txtTelefonoUsuario.setText(usuario.getTelefono());
            txtCorreoUsuario.setText(usuario.getCorreo());
            txtPassWordUsuario.setText(usuario.getContraseña());
            txtAdminUsuario.setText(String.valueOf(usuario.isAdministrador()));
        } catch (NullPointerException e) {
            System.err.println("ADVERTENCIA: No se ha seleccionado ningún empleado.");
        }
    }

    public void buscarUsuarioTableView() {
        FilteredList<Usuario> filtroUsuario = new FilteredList<>(addUsuarioLista, u -> true);
        txtBusquedaUsuario.textProperty().addListener((observable, oldValue, newValue) -> {
            // Configurar el predicado para el filtrado
            filtroUsuario.setPredicate(usuario -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Convertir el valor de búsqueda a minúsculas para la comparación sin distinción de mayúsculas
                String lowerCaseFiltrer = newValue.toLowerCase();

                // Verificar si el valor de búsqueda se encuentra en alguno de los atributos del empleado
                if (usuario.getNombre().toLowerCase().indexOf(lowerCaseFiltrer) != -1) {
                    return true;
                } else if (usuario.getApellido().toLowerCase().indexOf(lowerCaseFiltrer) != -1) {
                    return true;
                } else if (usuario.getTelefono().toLowerCase().indexOf(lowerCaseFiltrer) != -1) {
                    return true;
                } else if (usuario.getCorreo().toLowerCase().indexOf(lowerCaseFiltrer) != -1) {
                    return true;
                } else if (usuario.getContraseña().toString().toLowerCase().indexOf(lowerCaseFiltrer) != -1) {
                    return true;
                } else if (usuario.getFechaAlta().toString().toLowerCase().indexOf(lowerCaseFiltrer) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<Usuario> listaOrdenadorUsuario = new SortedList<>(filtroUsuario);
        listaOrdenadorUsuario.comparatorProperty().bind(TableViewUsuario.comparatorProperty());
        TableViewUsuario.setItems(listaOrdenadorUsuario);
    }


    // Logica Ventana Principal Vehiculo

    @FXML
    void btnAddVehiculo(ActionEvent event) {

        String sql = "INSERT INTO vehiculo (Matricula, Marca_id, Modelo, Año, Usuario_id, Tipo_Vehiculo_id) VALUES (?, ?, ?, ?, ?, ?)";
        conexion = cbd.abrirConexion();

        // Comprobar que los campos no estan vacios
        if (!txtMatriculaVehicula.getText().isEmpty() || !txtModeloVehiculo.getText().isEmpty() || txtMarcaVehiculo.getValue() == null ||
                !txtAñoVehiculo.getText().isEmpty() || !txtIdUsuarioVehiculo.getText().isEmpty() || txtTipoVehiculoVehiculo.getValue() == null) {
            // Comprobar validaciones de campos
            if (!validarMatricula(txtMatriculaVehicula) || !validarAño(txtAñoVehiculo)) return;

            // Consulta para comprobar que el vehiculo no se encuentra en la bbdd
            String sqlComprobacion = "SELECT * from vehiculo v WHERE v.Matricula = ?";

            try {
                // Comprobación de duplicados
                sentencia = conexion.prepareStatement(sqlComprobacion);
                sentencia.setString(1, txtMatriculaVehicula.getText());
                resultado = sentencia.executeQuery();

                if (!resultado.next()) {
                    // Insertar nuevo vehículo
                    sentencia = conexion.prepareStatement(sql);

                    // Obtener el ID de la marca del vehículo seleccionado
                    MarcaVehiculo marcaVehiculo = txtMarcaVehiculo.getValue();
                    int idMarcaVehiculo = marcaVehiculo.getId();

                    // Obtener el ID del tipo de vehículo seleccionado
                    TipoVehiculo tipoVehiculoSeleccionado = txtTipoVehiculoVehiculo.getValue();
                    int idTipoVehiculo = tipoVehiculoSeleccionado.getId();

                    sentencia.setString(1, txtMatriculaVehicula.getText());
                    sentencia.setInt(2, idMarcaVehiculo);
                    sentencia.setString(3, txtModeloVehiculo.getText());
                    sentencia.setString(4, txtAñoVehiculo.getText());
                    sentencia.setString(5, txtIdUsuarioVehiculo.getText());
                    sentencia.setInt(6, idTipoVehiculo);

                    // Ejecutar la inserción
                    int filasInsertadas = sentencia.executeUpdate();

                    // Verificar si se insertó correctamente
                    if (filasInsertadas > 0) {
                        mostrarAlerta("Añadido", "El vehiculo ha sido añadido", Alert.AlertType.INFORMATION);
                        btnCleanUsuarios(event);
                        agregarVehiculoLista();
                    } else {
                        mostrarAlerta("Error", "No se pudo añadir el vehiculo", Alert.AlertType.ERROR);
                    }
                } else {
                    mostrarAlerta("Error", "El vehiculo ya existe", Alert.AlertType.ERROR);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                cerrarConexion(cbd);
            }
        } else {
            mostrarAlerta("Error", "Rellena los campos", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void btnUpdateVehiculo(ActionEvent event) {
        String sql = "UPDATE vehiculo SET Matricula = ?, Marca_id = ?, Modelo = ?, Año = ?, Usuario_id = ?, Tipo_Vehiculo_id = ? WHERE Matricula = ?";
        conexion = cbd.abrirConexion();

        // Verificar que los campos no estén vacíos
        if (!txtMatriculaVehicula.getText().isEmpty() && !txtModeloVehiculo.getText().isEmpty() && txtMarcaVehiculo.getValue() != null &&
                !txtAñoVehiculo.getText().isEmpty() && !txtIdUsuarioVehiculo.getText().isEmpty() && txtTipoVehiculoVehiculo.getValue() != null) {
            // Validar matrícula y año
            if (!validarMatricula(txtMatriculaVehicula) || !validarAño(txtAñoVehiculo)) return;

            // Verificar si el ID de usuario existe en la base de datos
            int idUsuario = Integer.parseInt(txtIdUsuarioVehiculo.getText());
            if (!existeUsuario(idUsuario)) {
                mostrarAlerta("Error", "El ID de usuario no existe en la base de datos.", Alert.AlertType.ERROR);
                return;
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Actualizar vehiculo");
            alert.setContentText("¿Estás seguro que quieres actualizar los cambios?");
            ButtonType opcion = alert.showAndWait().orElse(ButtonType.CANCEL);

            if (opcion == ButtonType.OK) {
                try {
                    // Obtener el ID de la marca del vehículo seleccionado
                    int idMarcaVehiculo = obtenerIdMarca(txtMarcaVehiculo.getValue());

                    // Obtener el ID del tipo de vehículo seleccionado
                    int idTipoVehiculo = obtenerIdTipoVehiculo(txtTipoVehiculoVehiculo.getValue());

                    sentencia = conexion.prepareStatement(sql);
                    sentencia.setString(1, txtMatriculaVehicula.getText());
                    sentencia.setInt(2, idMarcaVehiculo);
                    sentencia.setString(3, txtModeloVehiculo.getText());
                    sentencia.setString(4, txtAñoVehiculo.getText());
                    sentencia.setInt(5, Integer.parseInt(txtIdUsuarioVehiculo.getText()));
                    sentencia.setInt(6, idTipoVehiculo);
                    sentencia.setString(7, txtMatriculaVehicula.getText());

                    int resultado = sentencia.executeUpdate();
                    if (resultado > 0) {
                        mostrarAlerta("Vehículo actualizado", "El vehículo ha sido actualizado correctamente.", Alert.AlertType.INFORMATION);
                        agregarVehiculoLista();
                        btnCleanVehiculo(event);
                    } else {
                        mostrarAlerta("Error", "No se ha podido actualizar el vehículo.", Alert.AlertType.ERROR);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    cerrarConexion(cbd);
                }
            }
        } else {
            mostrarAlerta("Campos vacíos", "Por favor, complete todos los campos.", Alert.AlertType.WARNING);
        }
    }

    // Método para obtener el ID de la marca seleccionada
    private int obtenerIdMarca(MarcaVehiculo marcaSeleccionada) {
        int idMarca = 0;
        String sql = "SELECT id FROM marcavehiculo WHERE Nombre = ?";
        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, marcaSeleccionada.getNombre());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                idMarca = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idMarca;
    }

    // Método para obtener el ID del tipo de vehículo seleccionado
    private int obtenerIdTipoVehiculo(TipoVehiculo tipoVehiculoSeleccionado) {
        int idTipoVehiculo = 0;
        String sql = "SELECT id FROM tipo_vehiculo WHERE Nombre = ?";
        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, tipoVehiculoSeleccionado.getNombre());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                idTipoVehiculo = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idTipoVehiculo;
    }

    // Método para verificar si el ID de usuario existe en la base de datos
    private boolean existeUsuario(int idUsuario) {
        String sql = "SELECT id FROM datos_usuario WHERE id = ?";
        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, idUsuario);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Retorna true si el usuario existe, false si no
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @FXML
    void btnDeleteVehiculo(ActionEvent event) {

        String sql = "DELETE FROM vehiculo WHERE Matricula = ?";
        conexion = cbd.abrirConexion();

        if (!txtMatriculaVehicula.getText().isEmpty() || !txtMarcaVehiculo.getSelectionModel().isEmpty() || !txtModeloVehiculo.getText().isEmpty() ||
                txtAñoVehiculo.getText().isEmpty() || !txtTipoVehiculoVehiculo.getSelectionModel().isEmpty() || txtIdUsuarioVehiculo.getText().isEmpty()) {

            Alert alertaEliminar = new Alert(Alert.AlertType.CONFIRMATION);
            alertaEliminar.setTitle("Confirmar eliminación");
            alertaEliminar.setContentText("¿Estás seguro que quieres eliminar al usuario?");
            Optional<ButtonType> opcion = alertaEliminar.showAndWait();

            if (opcion.get() == ButtonType.OK) {
                try {
                    sentencia = conexion.prepareStatement(sql);
                    sentencia.setString(1, txtMatriculaVehicula.getText());
                    sentencia.executeUpdate();
                    mostrarAlerta("Eliminado con éxito", "El empleado ha sido eliminado con éxito", Alert.AlertType.INFORMATION);
                    btnCleanVehiculo(event);
                    agregarVehiculoLista();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    cerrarConexion(cbd);
                }
            }
        } else {
            mostrarAlerta("Error", "Rellena los campos", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void btnCleanVehiculo(ActionEvent event) {

        txtMatriculaVehicula.clear();
        txtMarcaVehiculo.setValue(null);
        txtModeloVehiculo.clear();
        txtAñoVehiculo.clear();
        txtTipoVehiculoVehiculo.setValue(null);
        txtIdUsuarioVehiculo.clear();
    }

    public ObservableList<Vehiculo> addVehiculo() {

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

    public void agregarVehiculoLista() {
        addVehiculoLista = addVehiculo();
        columnMatriculaVehiculo.setCellValueFactory(new PropertyValueFactory<>(COLUMNMATRICULAVEHICULO));
        columnMarcaVehiculo.setCellValueFactory(new PropertyValueFactory<>(COLUMNMARCAVEHICULO));
        columnModeloVehiculo.setCellValueFactory(new PropertyValueFactory<>(COLUMNMODELOVEHICULO));
        columnAñoVehiculo.setCellValueFactory(new PropertyValueFactory<>(COLUMNAÑOVEHICULO));
        columTipoVehiculoVehiculo.setCellValueFactory(new PropertyValueFactory<>(COLUMNTIPOVEHICULOVEHICULO));
        columPropietarioVehiculo.setCellValueFactory(new PropertyValueFactory<>(COLUMNPROPIETARIOVEHICULO));
        TableViewVehiculo.setItems(addVehiculoLista);
    }

    public void mostrarVehiculoSeleccionado() {
        Vehiculo vehiculo = TableViewVehiculo.getSelectionModel().getSelectedItem();
        int indice = TableViewVehiculo.getSelectionModel().getSelectedIndex();
        if (indice < 0) return;

        MarcaVehiculo marcaVehiculo = new MarcaVehiculo(vehiculo.getMarca());
        TipoVehiculo tipoVehiculo = new TipoVehiculo(vehiculo.getTipoVehiculo());

        // Aquí cargamos el usuario completo desde la base de datos usando su correo electrónico
        Usuario idUsuario = cargarUsuarioPorCorreo(vehiculo.getCorreoUsuario());

        txtMatriculaVehicula.setText(vehiculo.getMatricula());
        txtMarcaVehiculo.setValue(marcaVehiculo);
        txtModeloVehiculo.setText(vehiculo.getModelo());
        txtAñoVehiculo.setText(vehiculo.getYear());
        txtIdUsuarioVehiculo.setText(String.valueOf(idUsuario.getId()));
        txtTipoVehiculoVehiculo.setValue(tipoVehiculo);
    }

    public Usuario cargarUsuarioPorCorreo(String correo) {
        String sql = "SELECT * FROM datos_usuario WHERE correo = ?";
        Usuario usuario = null;
        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setString(1, correo);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String telefono = resultSet.getString("telefono");
                String contraseña = resultSet.getString("contraseña");
                boolean administrador = resultSet.getBoolean("administrador");
                String fechaAlta = resultSet.getString("fechaAlta");

                usuario = new Usuario(id, nombre, apellido, telefono, correo, contraseña, administrador, fechaAlta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public void buscarVehiculoTableView() {
        FilteredList<Vehiculo> filtroVehiculo = new FilteredList<>(addVehiculoLista, u -> true);
        txtBusquedaVehiculo.textProperty().addListener((observable, oldValue, newValue) -> {
            filtroVehiculo.setPredicate(vehiculo1 -> {
                if (newValue == null || newValue.isEmpty()) return true;
                String lowerCaseFiltrer = newValue.toLowerCase();

                if (vehiculo1.getMatricula().toLowerCase().indexOf(lowerCaseFiltrer) != -1) {
                    return true;
                } else if (vehiculo1.getMarca().toLowerCase().indexOf(lowerCaseFiltrer) != -1) {
                    return true;
                } else if (vehiculo1.getModelo().toLowerCase().indexOf(lowerCaseFiltrer) != -1) {
                    return true;
                } else if (vehiculo1.getYear().toLowerCase().indexOf(lowerCaseFiltrer) != -1) {
                    return true;
                } else if (vehiculo1.getTipoVehiculo().toLowerCase().indexOf(lowerCaseFiltrer) != -1) {
                    return true;
                } else if (vehiculo1.getCorreoUsuario().toLowerCase().indexOf(lowerCaseFiltrer) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<Vehiculo> listaOrdenadaVehiculo = new SortedList<>(filtroVehiculo);
        listaOrdenadaVehiculo.comparatorProperty().bind(TableViewVehiculo.comparatorProperty());
        TableViewVehiculo.setItems(listaOrdenadaVehiculo);
    }


    //Logica Ventana Principal Perfil

    public void asignarDatosUsuarioSesion() {

        Usuario usuario = Sesion.getUsuarioActual();
        txtIdUsuarioVehiculo.setText(String.valueOf(usuario.getId()));
        if (usuario != null) {
            txtNombrePerfil.setText(usuario.getNombre());
            txtApellidoPerfil.setText(usuario.getApellido());
            txtTelefonoPerfil.setText(usuario.getTelefono());
            txtCorreoPerfil.setText(usuario.getCorreo());
        } else {
            txtNombrePerfil.clear();
            txtApellidoPerfil.clear();
            txtTelefonoPerfil.clear();
            txtCorreoPerfil.clear();
        }
    }

    @FXML
    void btnModificarPasswordPerfil(ActionEvent event) {

        Stage stage = (Stage) txtNombrePerfil.getScene().getWindow();
        VentanaCambiarPassworduser vcp = new VentanaCambiarPassworduser();
        Stage modal = new Stage();
        modal.initModality(Modality.WINDOW_MODAL);
        modal.initOwner(stage);

        try {
            new VentanaCambiarPassword().start(modal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        contadorTotalUsuarios();
        contadorTotalCitas();
        contardorTotalVehiculos();
        contadorTotalGanaciasMensuales();
        cargarDatosGraficoUsuario();
        agregarUsuarioLista();
        agregarVehiculoLista();
        agregarCitaLista();
        mostrarUsuarioSeleccionado();
        mostrarVehiculoSeleccionado();
        buscarUsuarioTableView();
        buscarVehiculoTableView();
        asignarDatosUsuarioSesion();
        sacarNombreUsuarioLogueado(lblNombreUsuario);
        cargarDatosTipoVehiculo(txtTipoVehiculoVehiculo, cbd);
        cargarDatosTipoVehiculo(txtTipoVehiculoCita, cbd);
        cargarDatosMarcaVehiculo(txtMarcaVehiculo, cbd);
        cargarHorasCitas();
        cargarDatosTipoInspeccion(txtTipoInspeccionCita, cbd);

        // Escuchador para comprobar que la fecha esta seleccionada
        txtFechaCita.valueProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                comprobarFechaIsSelected(txtFechaCita,txtHoraCita);
            }
        });
        comprobarFechaIsSelected(txtFechaCita,txtHoraCita);
    }
}

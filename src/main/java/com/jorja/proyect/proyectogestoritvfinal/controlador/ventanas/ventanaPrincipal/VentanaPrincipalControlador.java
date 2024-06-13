package com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaPrincipal;

import com.jorja.proyect.proyectogestoritvfinal.controlador.bbdd.CONEXIONBD;
import com.jorja.proyect.proyectogestoritvfinal.modelo.*;
import com.jorja.proyect.proyectogestoritvfinal.vista.Main;
import com.jorja.proyect.proyectogestoritvfinal.vista.VentanaCambiarPassword;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.jorja.proyect.proyectogestoritvfinal.controlador.Utils.*;
import static com.jorja.proyect.proyectogestoritvfinal.controlador.bbdd.CONEXIONBD.comprobarConexion;
import static com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaPrincipal.VentanaPrincipalCitaControlador.*;
import static com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaPrincipal.VentanaPrincipalInicioControlador.contadorTarjetas;
import static com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaPrincipal.VentanaPrincipalInicioControlador.obtenerNombreMes;
import static com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaPrincipal.VentanaPrincipalPerfilControlador.*;
import static com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaPrincipal.VentanaPrincipalUsuarioControlador.*;
import static com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaPrincipal.VentanaPrincipalVehiculoControlador.*;

public class VentanaPrincipalControlador implements Initializable {

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;
    private CONEXIONBD cbd;
    private Date fecha;
    private Usuario usuario;
    private Vehiculo vehiculo;
    private Cita cita;
    private HistorialCita historialCita;

    public VentanaPrincipalControlador() {
        cbd = new CONEXIONBD();
    }

    // Elementos Lateral
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

    // Elementos Cita
    @FXML
    private TableView<Cita> TableViewCita;
    private ObservableList<Cita> addCitaLista;
    @FXML
    private TableColumn<Cita, String> columnIdCita;
    @FXML
    private TableColumn<Cita, String> columnMatriculaCita;
    @FXML
    private TableColumn<Cita, String> columnPrecioCita;
    @FXML
    private TableColumn<Cita, String> columnTipoInspeccionCita;
    @FXML
    private TableColumn<Cita, String> columnTipoVehiculoCita;
    @FXML
    private TableColumn<Cita, String> columnFechaCita;
    @FXML
    private TableColumn<Cita, String> columnHoraCita;
    @FXML
    private TableColumn<Cita, String> columnActivaCita;
    @FXML
    private TextField txtBusquedaCita;

    // Elementos Usuario
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

    // Elementos Historial
    @FXML
    private TextField txtBusquedaHistorial;
    private ObservableList<HistorialCita> addHistorialCitaLista;
    @FXML
    private TableView<HistorialCita> TableViewHistorial;
    @FXML
    private TableColumn<HistorialCita, String> columnMatriculaHistorial;
    @FXML
    private TableColumn<HistorialCita, String> columnFechaHistorial;
    @FXML
    private TableColumn<HistorialCita, String> columnHoraHistorial;
    @FXML
    private TableColumn<HistorialCita, String> columnTipoInspeccionHistorial;

    // Otros Elementos
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

    // TextFields
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
    private TextField txtIdCita;
    @FXML
    private DatePicker txtFechaCita;
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
    private TextField txtActivaCita;
    @FXML
    private TextField txtTelefonoPerfil;
    @FXML
    private TextField txtTelefonoUsuario;
    @FXML
    private TextField txtTipoVehiculoCita;
    @FXML
    private CheckBox checkBoxAdmin;

    // ComboBoxes
    @FXML
    private ComboBox<MarcaVehiculo> txtMarcaVehiculo;
    @FXML
    private ComboBox<TipoInspeccion> txtTipoInspeccionCita;
    @FXML
    private ComboBox<TipoVehiculo> txtTipoVehiculoVehiculo;
    @FXML
    private ComboBox<String> txtHoraCita;


    @FXML
    void btnMinimizar(ActionEvent event) {
        Stage stage = (Stage) layoutPadre.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void btnSalir(ActionEvent event) {
        System.exit(0);
    }

    /***
     * Metodo para cambiar de ventana y cuando cambies se actualice los
     * componentes
     * @param actionEvent
     */
    public void cambiarVentana(ActionEvent actionEvent) {

        Button botonPresionado = (Button) actionEvent.getSource();
        Button[] botones = {btnLateralInicio, btnLateralUsuarios, btnLateralCita, btnLateralVehiculo, btnLateralPerfil};
        Pane[] layouts = {layoutInicio, layoutUsuarios, layoutPedirCita, layoutVehiculo, layoutPerfil};
        cambiarVentanaBtn(botonPresionado, botones, layouts);

        contadorTotalUsuarios();
        contadorTotalCitas();
        contardorTotalVehiculos();
        contadorTotalGanaciasMensuales();
        cargarDatosGraficoUsuario();
        agregarUsuarioLista();
        agregarVehiculoLista();
        agregarCitaLista();
        buscarUsuarioTableView();
        buscarVehiculoTableView();
        buscarCitaTableView();
        buscarHistorialCitaTableView();
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
        txtBusquedaCita.clear();
        txtBusquedaHistorial.clear();
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
        contadorTarjetas(sqlUsuarios, lblCountUser, cbd);
    }

    public void contardorTotalVehiculos() {
        String sqlVehiculos = "SELECT COUNT(v.matricula) as total From vehiculo v";
        contadorTarjetas(sqlVehiculos, lblCountvehicle, cbd);
    }

    public void contadorTotalCitas() {
        String sqlCitas = "SELECT COUNT(c.id) as total FROM cita C";
        contadorTarjetas(sqlCitas, lblCountDate, cbd);
    }

    public void contadorTotalGanaciasMensuales() {
        String sqlGanancias = """
                SELECT SUM(ti.Precio) AS total
                FROM cita c
                        INNER JOIN tipo_inspeccion ti ON c.Tipo_Inspeccion_id = ti.id
                                WHERE YEAR(c.Fecha) = YEAR(CURDATE()) 
                                        AND MONTH(c.Fecha) = MONTH(CURDATE());                          
                """;
        contadorTarjetas(sqlGanancias, lblCountMoney, cbd);
    }

    /***
     * Metodo para cargar los datos de usuarios que hay registrados en el grafico
     */
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
        String sqlCita = "INSERT INTO cita (Fecha, Hora, id_Vehiculo, Tipo_Inspeccion_id, Tipo_Vehiculo_id, Activa) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlHistorial = "INSERT INTO historial_inspecciones (Fecha, Hora, id_Vehiculo, Tipo_Inspeccion_id) VALUES (?, ?, ?, ?)";
        conexion = cbd.abrirConexion();
        comprobarConexion(conexion);

        // Comprobar que los campos no están vacíos
        if (txtMatriculaCita.getText().isEmpty() || txtFechaCita.getValue() == null || txtHoraCita.getValue() == null ||
                txtTipoInspeccionCita.getValue() == null) {
            mostrarAlerta("Campos vacíos", "Por favor, complete todos los campos.", Alert.AlertType.WARNING);
            return;
        }

        // Comprobar validaciones de campo
        if (!validarMatricula(txtMatriculaCita) || !validarFechaCita(txtFechaCita)) return;

        // Consulta para comprobar que esa cita no esté registrada
        String sqlComprobacion = "SELECT * FROM CITA WHERE Fecha = ? AND Hora = ?";
        String sqlVerificarVehiculo = "SELECT * FROM vehiculo WHERE Matricula = ?";

        try {
            // Verificar si el vehículo existe en la tabla vehiculo
            sentencia = conexion.prepareStatement(sqlVerificarVehiculo);
            sentencia.setString(1, txtMatriculaCita.getText().toUpperCase());
            resultado = sentencia.executeQuery();
            if (!resultado.next()) {
                mostrarAlerta("Vehículo no registrado", "La matrícula introducida no se encuentra en la base de datos", Alert.AlertType.ERROR);
                return;
            }

            // Verificar si la cita ya está registrada
            sentencia = conexion.prepareStatement(sqlComprobacion);
            sentencia.setString(1, String.valueOf(txtFechaCita.getValue()));
            sentencia.setString(2, txtHoraCita.getValue());
            resultado = sentencia.executeQuery();

            if (!resultado.next()) {
                // Iniciar una transacción
                conexion.setAutoCommit(false);

                // Insertar en la tabla cita
                sentencia = conexion.prepareStatement(sqlCita);

                // Obtener el ID del tipo vehículo seleccionado
                TipoVehiculo tipoVehiculoSeleccionado = obtenerTipoVehiculoPorMatricula(txtMatriculaCita.getText(),cbd);
                int idTipoVehiculo = tipoVehiculoSeleccionado.getId();

                // Obtener el ID del tipo inspección seleccionado
                TipoInspeccion tipoInspeccionSeleccionado = txtTipoInspeccionCita.getValue();
                int idTipoInspeccion = tipoInspeccionSeleccionado.getId();

                sentencia.setString(1, String.valueOf(txtFechaCita.getValue()));
                sentencia.setString(2, txtHoraCita.getValue());
                sentencia.setString(3, txtMatriculaCita.getText().toUpperCase());
                sentencia.setInt(4, idTipoInspeccion);
                sentencia.setInt(5, idTipoVehiculo);
                sentencia.setBoolean(6, true);

                int filasCita = sentencia.executeUpdate();
                if (filasCita > 0) {
                    // Insertar en la tabla historial_inspecciones
                    sentencia = conexion.prepareStatement(sqlHistorial);
                    sentencia.setString(1, String.valueOf(txtFechaCita.getValue()));
                    sentencia.setString(2, txtHoraCita.getValue());
                    sentencia.setString(3, txtMatriculaCita.getText().toUpperCase());
                    sentencia.setInt(4, idTipoInspeccion);

                    int filasHistorial = sentencia.executeUpdate();
                    if (filasHistorial > 0) {
                        // Confirmar la transacción
                        conexion.commit();

                        mostrarAlerta("Añadida", "La cita ha sido añadida correctamente", Alert.AlertType.INFORMATION);
                        agregarCitaLista();
                        agregarHistorialLista();
                        buscarCitaTableView();

                        // Recargar las horas disponibles para la fecha seleccionada utilizando obtenerHorasOcupadas
                        LocalDate fechaSeleccionada = txtFechaCita.getValue();
                        List<String> horasDisponibles = obtenerHorasOcupadas(fechaSeleccionada, cbd);
                        cargarHorasComboBox(txtHoraCita, horasDisponibles);
                        btnCleanCita(event);
                        buscarCitaTableView();
                    } else {
                        // Revertir la transacción si la inserción en historial falla
                        conexion.rollback();
                        mostrarAlerta("Error", "No se pudo añadir la cita en el historial", Alert.AlertType.ERROR);
                    }
                } else {
                    mostrarAlerta("Error", "No se pudo añadir la cita", Alert.AlertType.ERROR);
                }
            } else {
                mostrarAlerta("Error", "La cita ya existe", Alert.AlertType.ERROR);
            }
        } catch (SQLException e) {
            try {
                // Revertir la transacción en caso de excepción
                conexion.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            cerrarConexion(cbd);
        }
    }




    @FXML
    void btnUpdateCita(ActionEvent event) {

        String sql = "UPDATE cita SET id_Vehiculo = ?, Fecha = ?, Hora = ?,  Tipo_Inspeccion_id = ?, Tipo_Vehiculo_id = ?, Activa = ? WHERE id = ?";
        conexion = cbd.abrirConexion();
        comprobarConexion(conexion);

        // Comprobar que los campos no están vacíos
        if (txtMatriculaCita.getText().isEmpty() || txtFechaCita.getValue() == null || txtHoraCita.getValue() == null || txtTipoInspeccionCita.getValue() == null) {
            mostrarAlerta("Campos vacíos", "Por favor, complete todos los campos.", Alert.AlertType.WARNING);
            return;
        }

        // Comprobar validaciones de campo
        if (!validarMatricula(txtMatriculaCita) || !validarFechaCita(txtFechaCita)) {
            return;
        }

        // Verificar si el ID de la cita existe en la base de datos
        int idCita = Integer.parseInt(txtIdCita.getText());
        if (!existeCita(idCita)) {
            mostrarAlerta("Error", "El ID de cita no existe en la base de datos", Alert.AlertType.ERROR);
            return;
        }

        String sqlVerificarVehiculo = "SELECT * FROM vehiculo WHERE Matricula = ?";

        // Verificar si el vehículo existe en la tabla vehiculo
        try {
            sentencia = conexion.prepareStatement(sqlVerificarVehiculo);
            sentencia.setString(1, txtMatriculaCita.getText().toUpperCase());
            resultado = sentencia.executeQuery();
            if (!resultado.next()) {
                mostrarAlerta("Vehículo no registrado", "La matrícula introducida no se encuentra en la base de datos", Alert.AlertType.ERROR);
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Actualizar vehiculo");
        alert.setContentText("¿Estás seguro que quieres actualizar los cambios?");
        ButtonType opcion = alert.showAndWait().orElse(ButtonType.CANCEL);

        // Si la opcion seleccionada es igual a ok realizamos el update
        if (opcion == ButtonType.OK) {
            try {
                // Obtener el id del tipo inspeccion seleccionada
                int idTipoInspeccion = obtenerIdTipoInspeccion(txtTipoInspeccionCita.getValue());

                // Obtener el ID del tipo vehículo seleccionado
                TipoVehiculo tipoVehiculoSeleccionado = obtenerTipoVehiculoPorMatricula(txtMatriculaCita.getText(), cbd);
                int idTipoVehiculo = tipoVehiculoSeleccionado.getId();

                sentencia = conexion.prepareStatement(sql);
                sentencia.setString(1,txtMatriculaCita.getText().toUpperCase());
                sentencia.setString(2, String.valueOf(txtFechaCita.getValue()));
                sentencia.setString(3, txtHoraCita.getValue());
                sentencia.setInt(4, idTipoInspeccion);
                sentencia.setInt(5, idTipoVehiculo);
                sentencia.setBoolean(6, Boolean.parseBoolean(txtActivaCita.getText()));
                sentencia.setInt(7, Integer.parseInt(txtIdCita.getText()));

                int resultado = sentencia.executeUpdate();

                if (resultado > 0) {
                    mostrarAlerta("Cita actualizada", "La cita ha sido actualizada con éxito", Alert.AlertType.INFORMATION);
                    agregarCitaLista();
                    btnCleanCita(event);
                    buscarCitaTableView();
                } else {
                    mostrarAlerta("Error", "No se ha podido actualizar la cita", Alert.AlertType.ERROR);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                cerrarConexion(cbd);
            }
        }
    }

    @FXML
    void btnDeleteCita(ActionEvent event) {

        String sql = "DELETE FROM cita WHERE id = ?";
        conexion = cbd.abrirConexion();

        // Comprobamos que los campos no estan vacios
        if (txtMatriculaCita.getText().isEmpty() || txtFechaCita.getValue() == null || txtHoraCita.getValue() == null ||
                txtTipoVehiculoCita.getText().isEmpty() || txtTipoInspeccionCita.getValue() == null || txtPrecioCita.getText().isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor, complete todos los campos.", Alert.AlertType.WARNING);
            return;
        }


        Alert alertaEliminar = new Alert(Alert.AlertType.CONFIRMATION);
        alertaEliminar.setTitle("Confirmar eliminación");
        alertaEliminar.setContentText("¿Estás seguro que quieres eliminar la cita?");
        Optional<ButtonType> opcion = alertaEliminar.showAndWait();
        // Si la opcion seleccionada es igual a ok realizamos el delete
        if (opcion.get() == ButtonType.OK) {
            try {
                sentencia = conexion.prepareStatement(sql);
                sentencia.setInt(1, Integer.parseInt(txtIdCita.getText()));
                sentencia.executeUpdate();
                mostrarAlerta("Eliminado con éxito", "La cita ha sido eliminada con éxito", Alert.AlertType.INFORMATION);
                agregarCitaLista();
                btnCleanCita(event);
                buscarCitaTableView();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                cerrarConexion(cbd);
            }
        }

    }

    @FXML
    void btnCleanCita(ActionEvent event) {
        txtIdCita.clear();
        txtMatriculaCita.clear();
        txtFechaCita.setValue(null);
        txtHoraCita.setValue(null);
        txtTipoVehiculoCita.clear();
        txtTipoInspeccionCita.setValue(null);
        txtPrecioCita.clear();
        txtActivaCita.clear();
        txtBusquedaCita.clear();

    }

    /***
     *  Metodo que comprueba la fecha de la cita, si la cita actual es mayor a la de la cita
     *  eliminara la cita de la tabla Citas, se mantendrá en el historial de movimientos
     */
    private void elimnarCitasPasadasDeTiempo(){
        eliminarCitasPasadas(cbd);
    }

    public void agregarCitaLista() {
        addCitaLista = obtenerListaCitaBD(cbd, cita);
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

    /***
     * Metodo para buscar los diferentes campos de cita esa cita
     */
    public void buscarCitaTableView() {
        FilteredList<Cita> filtroCita = new FilteredList<>(addCitaLista, u -> true);
        txtBusquedaCita.textProperty().addListener((observable, oldValue, newValue) -> {
            // Configurar el predicado para el filtrado
            filtroCita.setPredicate(cita -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Convertir el valor de búsqueda a minúsculas para la comparación sin distinción de mayúsculas
                String lowerCaseFiltrer = newValue.toLowerCase();

                // Verificar si el valor de búsqueda se encuentra en alguno de los atributos del empleado
                if (cita.getMatriculaVehiculo().toLowerCase().indexOf(lowerCaseFiltrer) != -1) {
                    return true;
                } else if (cita.getFecha().toLowerCase().indexOf(lowerCaseFiltrer) != -1) {
                    return true;
                } else if (cita.getHora().toLowerCase().indexOf(lowerCaseFiltrer) != -1) {
                    return true;
                } else if (cita.getPrecio().toLowerCase().indexOf(lowerCaseFiltrer) != -1) {
                    return true;
                } else if (cita.getTipoVehiculoId().toString().toLowerCase().indexOf(lowerCaseFiltrer) != -1) {
                    return true;
                } else if (cita.getTipoInspeccionId().toString().toLowerCase().indexOf(lowerCaseFiltrer) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<Cita> listaOrdenadaCita = new SortedList<>(filtroCita);
        listaOrdenadaCita.comparatorProperty().bind(TableViewCita.comparatorProperty());
        TableViewCita.setItems(listaOrdenadaCita);
    }


// Metodo para mostrar en los texfield/combobox los datos de la cita seleccionada
    public void mostrarCitaSeleccionada(){
        Cita cita = TableViewCita.getSelectionModel().getSelectedItem();
        int indice = TableViewCita.getSelectionModel().getSelectedIndex();

        if(indice<0)return;

        TipoVehiculo tipoVehiculo = new TipoVehiculo(cita.getTipoVehiculoId());
        TipoInspeccion tipoInspeccion = new TipoInspeccion(cita.getTipoInspeccionId());

        txtIdCita.setText(String.valueOf(cita.getId()));
        txtMatriculaCita.setText(cita.getMatriculaVehiculo());
        txtFechaCita.setValue(LocalDate.parse(cita.getFecha()));
        txtHoraCita.setValue(cita.getHora());
        txtTipoVehiculoCita.setText(cita.getTipoVehiculoId());
        txtTipoInspeccionCita.setValue(tipoInspeccion);
        txtPrecioCita.setText(cita.getPrecio());
        txtActivaCita.setText(String.valueOf(cita.isActiva()));

    }

    /***
     *  Cargar horas en el combobox, si las horas ya estan seleccionadas
     *  se mostraran unicamente las horas que hay disponibles
     * @param datePicker
     * @param comboBox
     * @param cbd
     */
    public void cargarHorasCitas(DatePicker datePicker, ComboBox<String> comboBox, CONEXIONBD cbd) {
        datePicker.setOnAction(event -> {
            LocalDate fechaSeleccionada = datePicker.getValue();
            if (fechaSeleccionada != null) {
                List<String> horasOcupadas = obtenerHorasOcupadas(fechaSeleccionada, cbd);
                cargarHorasComboBox(comboBox, horasOcupadas);
            }
        });
    }


    // Logica Ventana Principal Usuario
    @FXML
    void btnAddUsuario(ActionEvent event) {
        String sql = """
        INSERT INTO datos_usuario
         (Nombre, Apellido, Telefono, Correo, Contraseña, FechaAlta, Administrador)
          VALUES (?, ?, ?, ?, ?, CURRENT_DATE, ?)
        """;
        conexion = cbd.abrirConexion();
        comprobarConexion(conexion);

        if (txtNombreUsuario.getText().isEmpty() || txtApellidoUsuario.getText().isEmpty() || txtTelefonoUsuario.getText().isEmpty() ||
                txtCorreoUsuario.getText().isEmpty() || txtPassWordUsuario.getText().isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor, complete todos los campos.", Alert.AlertType.WARNING);
            return;
        }

            if (!validarTelefono(txtTelefonoUsuario) || !validarCorreo(txtCorreoUsuario) || !validarPassword(txtPassWordUsuario))
                return;

            String comprobarUsuariosql = "SELECT du.Correo FROM datos_usuario du where du.Correo = ?";
            try {
                sentencia = conexion.prepareStatement(comprobarUsuariosql);
                sentencia.setString(1, txtCorreoUsuario.getText());
                resultado = sentencia.executeQuery();

                if (resultado.next()) {
                    mostrarAlerta("Error", "El usuario ya existe", Alert.AlertType.ERROR);
                } else {
                    String hashedPassword = hashPassword(txtPassWordUsuario.getText());

                    sentencia = conexion.prepareStatement(sql);
                    sentencia.setString(1, toTitleCase(txtNombreUsuario.getText()));
                    sentencia.setString(2, toTitleCase(txtApellidoUsuario.getText()));
                    sentencia.setString(3, txtTelefonoUsuario.getText());
                    sentencia.setString(4, txtCorreoUsuario.getText());
                    sentencia.setString(5, hashedPassword);
                    sentencia.setBoolean(6, checkBoxAdmin.isSelected());

                    sentencia.executeUpdate();
                    mostrarAlerta("Añadido", "El usuario ha sido añadido", Alert.AlertType.INFORMATION);
                    agregarUsuarioLista();
                    btnCleanUsuarios(event);
                    buscarUsuarioTableView();

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                cerrarConexion(cbd);
            }
    }

    @FXML
    void btnUpdateUsuario(ActionEvent event) {
        String sqlSelect = "SELECT du.Contraseña FROM datos_usuario du WHERE du.id = ?";
        String sqlUpdate = "UPDATE datos_usuario du SET du.Nombre = ?, du.Apellido = ?, du.Telefono = ?, du.Correo = ?, du.Contraseña = ?, du.Administrador = ? WHERE du.id = ?";

        conexion = cbd.abrirConexion();
        comprobarConexion(conexion);

        // Comprobaciones de campos, si está vacío muestra alerta error, sino comprueba que los datos se han introducido con formato correcto
        if (txtNombreUsuario.getText().isEmpty() || txtApellidoUsuario.getText().isEmpty() || txtTelefonoUsuario.getText().isEmpty() ||
                txtCorreoUsuario.getText().isEmpty() || txtPassWordUsuario.getText().isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor, complete todos los campos.", Alert.AlertType.WARNING);
            return;
        }
            // Validar campos
            if (!validarTelefono(txtTelefonoUsuario) || !validarCorreo(txtCorreoUsuario) || !validarPassword(txtPassWordUsuario))
                return;

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Actualizar empleado");
            alert.setContentText("¿Estás seguro que quieres actualizar los cambios?");

            ButtonType opcion = alert.showAndWait().orElse(ButtonType.CANCEL);

            if (opcion == ButtonType.OK) {
                try {
                    String passwordBD = "";

                    // Sacamos la contraseña de la base de datos
                    sentencia = conexion.prepareStatement(sqlSelect);
                    sentencia.setString(1, txtIdUsuario.getText());
                     resultado = sentencia.executeQuery();
                    if (resultado.next()) {
                        passwordBD = resultado.getString("Contraseña");
                    }
                    resultado.close();
                    sentencia.close();
                    // Guardamos la nueva contraseña escrita en la caja de texto
                    String passwordNueva = txtPassWordUsuario.getText();

                    /* Hacemos una ternaria en la que si la contraseña que esta en bd es igual a la que esta en la caja de texto la deja tal cual,
                     *   pero si la contraseña no es igual la cifra de nuevo y la modifica
                     */
                    String hashedPassword = passwordBD.equals(passwordNueva) ? passwordBD : hashPassword(passwordNueva);

                    sentencia = conexion.prepareStatement(sqlUpdate);
                    sentencia.setString(1, toTitleCase(txtNombreUsuario.getText()));
                    sentencia.setString(2, toTitleCase(txtApellidoUsuario.getText()));
                    sentencia.setString(3, txtTelefonoUsuario.getText());
                    sentencia.setString(4, txtCorreoUsuario.getText());
                    sentencia.setString(5, hashedPassword);
                    sentencia.setBoolean(6, checkBoxAdmin.isSelected());
                    sentencia.setString(7, txtIdUsuario.getText());
                    sentencia.executeUpdate();

                    mostrarAlerta("Usuario actualizado", "El usuario ha sido actualizado", Alert.AlertType.INFORMATION);
                    agregarUsuarioLista();
                    btnCleanUsuarios(event);
                    buscarUsuarioTableView();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    cerrarConexion(cbd);
                }
            }
    }

    @FXML
    void btnDeleteUsuario(ActionEvent event) {

        String sql = "DELETE from datos_usuario WHERE Correo = ?";
        conexion = cbd.abrirConexion();
        comprobarConexion(conexion);

        if (txtNombreUsuario.getText().isEmpty() || txtApellidoUsuario.getText().isEmpty() || txtTelefonoUsuario.getText().isEmpty() ||
                txtCorreoUsuario.getText().isEmpty() || txtPassWordUsuario.getText().isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor, complete todos los campos.", Alert.AlertType.WARNING);
            return;
        }

            Alert alertaEliminar = new Alert(Alert.AlertType.CONFIRMATION);
            alertaEliminar.setTitle("Confirmar eliminación");
            alertaEliminar.setContentText("¿Estás seguro que quieres eliminar al usuario?");
            Optional<ButtonType> opcion = alertaEliminar.showAndWait();

            if (opcion.get() == ButtonType.OK) {
                try {
                    sentencia = conexion.prepareStatement(sql);
                    sentencia.setString(1, txtCorreoUsuario.getText());
                    sentencia.executeUpdate();
                    mostrarAlerta("Eliminado con éxito", "El usuario ha sido eliminado con éxito", Alert.AlertType.INFORMATION);
                    btnCleanUsuarios(event);
                    agregarUsuarioLista();
                    btnCleanUsuarios(event);
                    buscarUsuarioTableView();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    cerrarConexion(cbd);
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
       checkBoxAdmin.setSelected(false);
       txtBusquedaUsuario.clear();
    }
    public void agregarUsuarioLista() {
        // Obtenemos la lista de Usuario
        addUsuarioLista = addUsuario(cbd,usuario);

        //Configuramos las columnas
        columnIdUsuario.setCellValueFactory(new PropertyValueFactory<>(COLUMNIDUSUARIO));
        columnNombreUsuario.setCellValueFactory(new PropertyValueFactory<>(COLUMNNOMBREUSUARIO));
        columnApellidoUsuario.setCellValueFactory(new PropertyValueFactory<>(COLUMNAPELLIDOUSUARIO));
        columnTelefonoUsuario.setCellValueFactory(new PropertyValueFactory<>(COLUMNTELEFONOUSUARIO));
        columnCorreoUsuario.setCellValueFactory(new PropertyValueFactory<>(COLUMNCORREOUSUARIO));
        columnPasswordUsuario.setCellValueFactory(new PropertyValueFactory<>(COLUMNCONTRASEÑAUSUARIO));
        columnAdminUsuario.setCellValueFactory(new PropertyValueFactory<>(COLUMNADMINISTRADORUSUARIO));
        columnFechaAltaUsuario.setCellValueFactory(new PropertyValueFactory<>(COLUMNFECHAALTAUSUARIO));
        // Establece los items en el TableView
        TableViewUsuario.setItems(addUsuarioLista);
    }

    // Mostramos en los textfield/combobox el usuario seleccionado
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
            checkBoxAdmin.setSelected(usuario.isAdministrador());
        } catch (NullPointerException e) {
            System.err.println("ADVERTENCIA: No se ha seleccionado ningún empleado.");
        }
    }

    // Metodo para buscar al usuario segun los campos
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
        comprobarConexion(conexion);

        // Comprobar que los campos no están vacíos
        if (txtMatriculaVehicula.getText().isEmpty() && txtModeloVehiculo.getText().isEmpty() && txtMarcaVehiculo.getValue() == null &&
                txtAñoVehiculo.getText().isEmpty() && txtIdUsuarioVehiculo.getText().isEmpty() && txtTipoVehiculoVehiculo.getValue() == null) {
            mostrarAlerta("Campos vacíos", "Por favor, complete todos los campos.", Alert.AlertType.WARNING);
            return;
        }


            // Eliminar espacios en blanco de la matrícula
            String matricula = txtMatriculaVehicula.getText().replaceAll("\\s+", "").toUpperCase();

            // Comprobar validaciones de campos
            if (!validarMatricula(txtMatriculaVehicula) || !validarAño(txtAñoVehiculo)) return;

            // Consulta para comprobar que el vehículo no se encuentra en la BBDD
            String sqlComprobacion = "SELECT * FROM vehiculo v WHERE v.Matricula = ?";

            // Consulta para comprobar que el usuario existe en la BBDD
            String sqlComprobacionUsuario = "SELECT * FROM datos_usuario WHERE id = ?";

            try {
                // Comprobar si el usuario existe
                sentencia = conexion.prepareStatement(sqlComprobacionUsuario);
                sentencia.setString(1, txtIdUsuarioVehiculo.getText());
                resultado = sentencia.executeQuery();

                if (!resultado.next()) {
                    mostrarAlerta("Error", "El usuario no existe", Alert.AlertType.ERROR);
                    return;
                }

                // Comprobación de duplicados de vehículo
                sentencia = conexion.prepareStatement(sqlComprobacion);
                sentencia.setString(1, matricula);
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

                    sentencia.setString(1, matricula);
                    sentencia.setInt(2, idMarcaVehiculo);
                    sentencia.setString(3, toTitleCase(txtModeloVehiculo.getText()));
                    sentencia.setString(4, txtAñoVehiculo.getText());
                    sentencia.setString(5, txtIdUsuarioVehiculo.getText());
                    sentencia.setInt(6, idTipoVehiculo);

                    // Ejecutar la inserción
                    int filasInsertadas = sentencia.executeUpdate();

                    // Verificar si se insertó correctamente
                    if (filasInsertadas > 0) {
                        mostrarAlerta("Añadido", "El vehículo ha sido añadido", Alert.AlertType.INFORMATION);
                        agregarVehiculoLista();
                        btnCleanVehiculo(event);
                        buscarVehiculoTableView();

                    } else {
                        mostrarAlerta("Error", "No se pudo añadir el vehículo", Alert.AlertType.ERROR);
                    }
                } else {
                    mostrarAlerta("Error", "El vehículo ya existe", Alert.AlertType.ERROR);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                cerrarConexion(cbd);
            }
    }

    @FXML
    void btnUpdateVehiculo(ActionEvent event) {
        String sql = "UPDATE vehiculo SET Matricula = ?, Marca_id = ?, Modelo = ?, Año = ?, Usuario_id = ?, Tipo_Vehiculo_id = ? WHERE Matricula = ?";
        conexion = cbd.abrirConexion();
        comprobarConexion(conexion);

        // Verificar que los campos no estén vacíos
        if (txtMatriculaVehicula.getText().isEmpty() && txtModeloVehiculo.getText().isEmpty() && txtMarcaVehiculo.getValue() == null &&
                txtAñoVehiculo.getText().isEmpty() && txtIdUsuarioVehiculo.getText().isEmpty() && txtTipoVehiculoVehiculo.getValue() == null) {
            mostrarAlerta("Campos vacíos", "Por favor, complete todos los campos.", Alert.AlertType.WARNING);
            return;
        }

            String matricula = txtMatriculaVehicula.getText().replaceAll(" ", "").toUpperCase();
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
                    sentencia.setString(1, matricula);
                    sentencia.setInt(2, idMarcaVehiculo);
                    sentencia.setString(3, txtModeloVehiculo.getText().toUpperCase());
                    sentencia.setString(4, txtAñoVehiculo.getText());
                    sentencia.setInt(5, Integer.parseInt(txtIdUsuarioVehiculo.getText()));
                    sentencia.setInt(6, idTipoVehiculo);
                    sentencia.setString(7, matricula);

                    int resultado = sentencia.executeUpdate();
                    if (resultado > 0) {
                        mostrarAlerta("Vehículo actualizado", "El vehículo ha sido actualizado correctamente.", Alert.AlertType.INFORMATION);
                        agregarVehiculoLista();
                        btnCleanVehiculo(event);
                        buscarVehiculoTableView();
                    } else {
                        mostrarAlerta("Error", "No se ha podido actualizar el vehículo.", Alert.AlertType.ERROR);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    cerrarConexion(cbd);
                }
            }
    }

    @FXML
    void btnDeleteVehiculo(ActionEvent event) {

        String sql = "DELETE FROM vehiculo WHERE Matricula = ?";
        conexion = cbd.abrirConexion();
        comprobarConexion(conexion);

        if (txtMatriculaVehicula.getText().isEmpty() || txtMarcaVehiculo.getValue() == null || txtModeloVehiculo.getText().isEmpty() ||
                txtAñoVehiculo.getText().isEmpty() || txtTipoVehiculoVehiculo.getValue() == null || txtIdUsuarioVehiculo.getText().isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor, complete todos los campos.", Alert.AlertType.WARNING);
            return;
        }

            Alert alertaEliminar = new Alert(Alert.AlertType.CONFIRMATION);
            alertaEliminar.setTitle("Confirmar eliminación");
            alertaEliminar.setContentText("¿Estás seguro que quieres eliminar el vehículo?");
            Optional<ButtonType> opcion = alertaEliminar.showAndWait();

            if (opcion.get() == ButtonType.OK) {
                try {
                    sentencia = conexion.prepareStatement(sql);
                    sentencia.setString(1, txtMatriculaVehicula.getText());
                    sentencia.executeUpdate();
                    mostrarAlerta("Eliminado con éxito", "El vehículo ha sido eliminado con éxito", Alert.AlertType.INFORMATION);
                    agregarVehiculoLista();
                    btnCleanVehiculo(event);
                    buscarVehiculoTableView();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    cerrarConexion(cbd);
                }
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
        txtBusquedaVehiculo.clear();
    }


    public void agregarVehiculoLista() {

        // Obtenemos la lista de vehiculo
        addVehiculoLista = addVehiculo(cbd, vehiculo);

        // Configuramos las columnas del TableView
        columnMatriculaVehiculo.setCellValueFactory(new PropertyValueFactory<>(COLUMNMATRICULAVEHICULO));
        columnMarcaVehiculo.setCellValueFactory(new PropertyValueFactory<>(COLUMNMARCAVEHICULO));
        columnModeloVehiculo.setCellValueFactory(new PropertyValueFactory<>(COLUMNMODELOVEHICULO));
        columnAñoVehiculo.setCellValueFactory(new PropertyValueFactory<>(COLUMNAÑOVEHICULO));
        columTipoVehiculoVehiculo.setCellValueFactory(new PropertyValueFactory<>(COLUMNTIPOVEHICULOVEHICULO));
        columPropietarioVehiculo.setCellValueFactory(new PropertyValueFactory<>(COLUMNPROPIETARIOVEHICULO));
        // Establecemos los items en el TableView
        TableViewVehiculo.setItems(addVehiculoLista);
    }

    // Metodo para mostrar en los textfield/combobox la informacion del vehiculo seleccionado
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

    /***
     * Metodo para buscar en el tableview el vehiculo por sus campos
     */
    public void buscarVehiculoTableView() {
        FilteredList<Vehiculo> filtroVehiculo = new FilteredList<>(addVehiculoLista, u -> true);
        txtBusquedaVehiculo.textProperty().addListener((observable, oldValue, newValue) -> {
            filtroVehiculo.setPredicate(vehiculo -> {
                if (newValue == null || newValue.isEmpty()) return true;
                String lowerCaseFiltrer = newValue.toLowerCase();

                if (vehiculo.getMatricula().toLowerCase().indexOf(lowerCaseFiltrer) != -1) {
                    return true;
                } else if (vehiculo.getMarca().toLowerCase().indexOf(lowerCaseFiltrer) != -1) {
                    return true;
                } else if (vehiculo.getModelo().toLowerCase().indexOf(lowerCaseFiltrer) != -1) {
                    return true;
                } else if (vehiculo.getYear().toLowerCase().indexOf(lowerCaseFiltrer) != -1) {
                    return true;
                } else if (vehiculo.getTipoVehiculo().toLowerCase().indexOf(lowerCaseFiltrer) != -1) {
                    return true;
                } else if (vehiculo.getCorreoUsuario().toLowerCase().indexOf(lowerCaseFiltrer) != -1) {
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

    // Metodo para cargar en los datos del usuario que inicia sesion en la ventana perfil
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

    // Metodo para modificar la contraseña del usuario que ha iniciado sesion
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

    public void agregarHistorialLista(){

        // Obtiene la lista de historial de citas.
        addHistorialCitaLista = addHistorialCita(cbd,historialCita);

        // Configura las columnas de la TableView con los nombres de propiedad correspondientes.
        columnFechaHistorial.setCellValueFactory(new PropertyValueFactory<>(COLUMNFECHAHISTORIAL));
        columnHoraHistorial.setCellValueFactory(new PropertyValueFactory<>(COLUMNHORAHISTORIAL));
        columnMatriculaHistorial.setCellValueFactory(new PropertyValueFactory<>(COLUMNMATRICULAHISTORIAL));
        columnTipoInspeccionHistorial.setCellValueFactory(new PropertyValueFactory<>(COLUMNTIPOINSPECCIONHISTORIAL));

        // Establecemos los items en el tableview
        TableViewHistorial.setItems(addHistorialCitaLista);
    }

    public void buscarHistorialCitaTableView() {
        FilteredList<HistorialCita> filtroHistorial = new FilteredList<>(addHistorialCitaLista, u -> true);
        txtBusquedaHistorial.textProperty().addListener((observable, oldValue, newValue) -> {
            filtroHistorial.setPredicate(historialCita -> {
                if (newValue == null || newValue.isEmpty()) return true;
                String lowerCaseFiltrer = newValue.toLowerCase();

                if (historialCita.getMatricula().toLowerCase().indexOf(lowerCaseFiltrer) != -1) {
                    return true;
                } else if (historialCita.getFecha().toLowerCase().indexOf(lowerCaseFiltrer) != -1) {
                    return true;
                } else if (historialCita.getHora().toLowerCase().indexOf(lowerCaseFiltrer) != -1) {
                    return true;
                } else if (historialCita.getTipoInspeccionId().toLowerCase().indexOf(lowerCaseFiltrer) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<HistorialCita> listaOrdenadaHistorialCita = new SortedList<>(filtroHistorial);
        listaOrdenadaHistorialCita.comparatorProperty().bind(TableViewHistorial.comparatorProperty());
        TableViewHistorial.setItems(listaOrdenadaHistorialCita);
    }



    // Metodo para generar informes de usuarios
    @FXML
    void btnInformeUsuario(ActionEvent event) {
        crearInforme(INFORMEUSUARIO,cbd);
    }
    // Metodo para generar informes de citas
    @FXML
    void btnInformeCita(ActionEvent event) {
        crearInforme(INFORMECITA,cbd);
    }
    // Metodo para generar informes de vehiculos
    @FXML
    void btnInformeVehiculo(ActionEvent event) {
        crearInforme(INFORMEVEHICULO,cbd);
    }
    // Metodo para generar informes de historial
    @FXML
    void btnInformeHistorial(ActionEvent event){
        crearInforme(INFORMEHISTORIAL,cbd);
    }

    // Metodo para realizar la copia de seguridad de la base de datos
    @FXML
    void btnBackUpBD(ActionEvent event) {
        backUpBD(cbd);
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
        agregarHistorialLista();
        mostrarUsuarioSeleccionado();
        mostrarVehiculoSeleccionado();
        mostrarCitaSeleccionada();
        buscarUsuarioTableView();
        buscarVehiculoTableView();
        buscarCitaTableView();
        buscarHistorialCitaTableView();
        asignarDatosUsuarioSesion();
        sacarNombreUsuarioLogueado(lblNombreUsuario);
        cargarDatosTipoVehiculo(txtTipoVehiculoVehiculo, cbd);
        cargarDatosMarcaVehiculo(txtMarcaVehiculo, cbd);
        cargarHorasCitas(txtFechaCita,txtHoraCita,cbd);
        deshabilitarDiasNoValidos(txtFechaCita);
        cargarDatosTipoInspeccion(txtTipoInspeccionCita, cbd);
        comprobarFechaIsSelected(txtFechaCita,txtHoraCita);
        elimnarCitasPasadasDeTiempo();


        // Escuchador para comprobar que la fecha esta seleccionada, sino el combobox de horas estara deshabilitado
        txtFechaCita.valueProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                comprobarFechaIsSelected(txtFechaCita, txtHoraCita);
            }
        });


        // Listener para cargar las horas disponibles cuando cambia la fecha
        txtFechaCita.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                List<String> horasDisponibles = obtenerHorasOcupadas(newValue, cbd);
                cargarHorasComboBox(txtHoraCita, horasDisponibles);
            }
        });

        // Agregar listener al ComboBox de Tipo de Inspección, para cargar el precio de la cita segun la opcion que selecciones
        txtTipoInspeccionCita.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtPrecioCita.setText(String.valueOf(newValue.getPrecio()));
            }
        });

        // Listener para autocompletar el tipo de vehículo cuando se introduce la matrícula
        txtMatriculaCita.textProperty().addListener((observable, oldValue, newValue) -> {
            // Verifica si el nuevo valor (newValue) no está vacío y tiene exactamente 7 caracteres
            if (!newValue.isEmpty() && newValue.length() == 7) {
                // Realiza la consulta para obtener el tipo de vehículo asociado a la matrícula ingresada
                TipoVehiculo tipoVehiculo = obtenerTipoVehiculoPorMatricula(newValue, cbd);

                // Si se encuentra un tipo de vehículo correspondiente a la matrícula
                if (tipoVehiculo != null) {
                    // Establece el texto del campo txtTipoVehiculoCita2 al nombre del tipo de vehículo
                    txtTipoVehiculoCita.setText(String.valueOf(tipoVehiculo.getNombre()));
                } else {
                    // Si no se encuentra un tipo de vehículo, limpia el campo txtTipoVehiculoCita2
                    txtTipoVehiculoCita.setText(null);
                }
            } else {
                // Si el nuevo valor está vacío o no tiene 7 caracteres, limpia el campo txtTipoVehiculoCita2
                txtTipoVehiculoCita.setText(null);
            }
        });

    }
}

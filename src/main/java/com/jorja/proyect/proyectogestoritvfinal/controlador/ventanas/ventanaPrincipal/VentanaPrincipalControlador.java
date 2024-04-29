package com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaPrincipal;

import com.jorja.proyect.proyectogestoritvfinal.controlador.bbdd.CONEXIONBD;
import com.jorja.proyect.proyectogestoritvfinal.modelo.Sesion;
import com.jorja.proyect.proyectogestoritvfinal.modelo.Usuario;
import com.jorja.proyect.proyectogestoritvfinal.vista.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

public class VentanaPrincipalControlador implements Initializable {

    @FXML
    private Button btnLateralCita;

    @FXML
    private Button btnLateralInicio;

    @FXML
    private Button btnLateralPerfil;

    @FXML
    private Button btnLateralVehiculo;
    @FXML
    private BorderPane layoutPadre;

    @FXML
    private AnchorPane layoutInicio;

    @FXML
    private AnchorPane layoutPedirCita;

    @FXML
    private AnchorPane layoutPerfil;

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

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;
    private CONEXIONBD cbd;

    public VentanaPrincipalControlador() {
        cbd = new CONEXIONBD();

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
        Button[] botones = {btnLateralInicio, btnLateralCita, btnLateralVehiculo, btnLateralPerfil};
        Pane[] layouts = {layoutInicio, layoutPedirCita, layoutVehiculo, layoutPerfil};
        VentanaPrincipalInicioControlador.cambiarVentana(botonPresionado, botones, layouts);

        contadorTotalUsuarios();
        contadorTotalCitas();
        contardorTotalVehiculos();
        contadorTotalGanaciasMensuales();
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Tarjetas Contador
        contadorTotalUsuarios();
        contadorTotalCitas();
        contardorTotalVehiculos();
        contadorTotalGanaciasMensuales();

        // Sacar nombre usuario que ha iniciado sesion

        Usuario usuarioActual = Sesion.getUsuarioActual();
        if (usuarioActual != null) {
            lblNombreUsuario.setText(usuarioActual.getNombre());
        } else {
            lblNombreUsuario.setText("Usuario desconodido");
        }

    }
}

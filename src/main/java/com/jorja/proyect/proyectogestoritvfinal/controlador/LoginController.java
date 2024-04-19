package com.jorja.proyect.proyectogestoritvfinal.controlador;

import com.jorja.proyect.proyectogestoritvfinal.vista.VentanaPrincipal;
import com.jorja.proyect.proyectogestoritvfinal.vista.VentanaRegister;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;
    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;
    private CONEXIONBD cbd;

    @FXML
    void btnLogin(ActionEvent event) {
        iniciarSesion();
    }

    @FXML
    void btnClose(ActionEvent event) {

        System.exit(0);
    }

    @FXML
    void btnRegister(ActionEvent event) {

        registrar();
    }

    private void registrar() {

        txtEmail.getScene().getWindow().hide();

        try {
            new VentanaRegister().start(new Stage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void iniciarSesion() {
        String sql = "SELECT du.Correo, du.Contraseña, du.administrador FROM datos_usuario du WHERE du.Correo = ? AND du.Contraseña = ?";
        cbd = new CONEXIONBD();
        conexion = cbd.abrirConexion();

        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, txtEmail.getText());
            sentencia.setString(2, txtPassword.getText());
            resultado = sentencia.executeQuery();

            if (txtEmail.getText().isEmpty() || txtPassword.getText().isEmpty()) {
                mostrarAlerta("Campos Vacíos", "¡Campos vacíos! Por favor, completa todos los campos antes de continuar.");
            } else if (resultado.next()) {
                boolean esAdministrador = resultado.getBoolean("administrador");
                if (esAdministrador) {
                    // Si el usuario es administrador
                    txtEmail.getScene().getWindow().hide();
                    try {
                        new VentanaPrincipal().start(new Stage());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    // Si el usuario no es administrador
                    mostrarAlerta("Acceso Denegado", "No tienes permisos de administrador para acceder a esta aplicación.");
                }
            } else {
                mostrarAlerta("Error de Credenciales", "¡Error de credenciales! Por favor, verifica tus datos e inténtalo nuevamente.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conexion != null) {

                try {
                    cbd.cerrarConexion();
                    conexion.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtPassword.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                iniciarSesion();
            }
        });
    }
}

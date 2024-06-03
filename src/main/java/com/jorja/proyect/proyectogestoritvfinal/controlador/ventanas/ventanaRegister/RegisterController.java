package com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaRegister;

import com.jorja.proyect.proyectogestoritvfinal.controlador.bbdd.CONEXIONBD;
import com.jorja.proyect.proyectogestoritvfinal.vista.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
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

import static com.jorja.proyect.proyectogestoritvfinal.controlador.Utils.*;
import static com.jorja.proyect.proyectogestoritvfinal.controlador.bbdd.CONEXIONBD.comprobarConexion;

public class RegisterController implements Initializable {

    @FXML
    private Label lblPasswordError1;

    @FXML
    private Label lblPasswordError2;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtNombre;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtPassword2;

    @FXML
    private TextField txtTelefono;

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;
    private CONEXIONBD cbd;

    @FXML
    void btnRegister(ActionEvent event) {
        registrarUsuario();
    }

    @FXML
    void btnCancelar(ActionEvent event) {
        txtNombre.getScene().getWindow().hide();

        try {
            new Main().start(new Stage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnClose(ActionEvent event) {
        System.exit(0);
    }

    /***
     *  Metodo para registar el usuario:
     *
     *  1. Se comprueba que los datos del usuario no existen ya en la BBDD
     *  2. Se comprueba que los campos estan rellenos y que los campos de contraseña coinciden
     *  3. Si todo esta correcto se crea el usuario
     */
    private void registrarUsuario() {

        String sqlComprobarUsuario = "SELECT * FROM datos_usuario du WHERE du.correo = ?";
        cbd = new CONEXIONBD();

        conexion = cbd.abrirConexion();
        comprobarConexion(conexion);

        try {
            sentencia = conexion.prepareStatement(sqlComprobarUsuario);
            sentencia.setString(1, txtCorreo.getText());
            resultado = sentencia.executeQuery();

            if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() || txtTelefono.getText().isEmpty() || txtCorreo.getText().isEmpty() || txtPassword.getText().isEmpty() || txtPassword2.getText().isEmpty()) {
                mostrarAlerta("Campos Vacíos", "¡Campos vacíos! Por favor, completa todos los campos antes de continuar.", Alert.AlertType.ERROR);
            } else if (!txtPassword.getText().equals(txtPassword2.getText())) {
                lblPasswordError1.setVisible(true);
                lblPasswordError2.setVisible(true);

            } else if (!validarTelefono(txtTelefono) || !validarCorreo(txtCorreo) || !validarPassword(txtPassword)) {
                return;
            } else {
                if (resultado.next()) {
                    mostrarAlerta("Usuario Existente", "¡El usuario ya está registrado!", Alert.AlertType.ERROR);
                } else {
                    String hashedPassword = hashPassword(txtPassword.getText());

                    String sqlInsert = "INSERT INTO datos_usuario(Nombre, Apellido, Telefono, Correo, Contraseña, FechaAlta) VALUES (?, ?, ?, ?, ?, CURRENT_DATE)";
                    sentencia = conexion.prepareStatement(sqlInsert);
                    sentencia.setString(1, txtNombre.getText());
                    sentencia.setString(2, txtApellido.getText());
                    sentencia.setString(3, txtTelefono.getText());
                    sentencia.setString(4, txtCorreo.getText());
                    sentencia.setString(5, hashedPassword);
                    int filas = sentencia.executeUpdate();

                    if (filas > 0) {
                        mostrarAlerta("Usuario registrado", "El usuario se registro con exito, inicie sesion", Alert.AlertType.INFORMATION);
                        txtCorreo.getScene().getWindow().hide();
                        try {
                            new Main().start(new Stage());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        mostrarAlerta("Error", "¡Ocurrió un error al registrar el usuario!", Alert.AlertType.ERROR);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            cbd.cerrarConexion();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtPassword2.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                registrarUsuario();
            }
        });
    }
}

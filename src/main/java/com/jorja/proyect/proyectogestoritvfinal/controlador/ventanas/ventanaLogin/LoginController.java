package com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaLogin;

import com.jorja.proyect.proyectogestoritvfinal.controlador.bbdd.CONEXIONBD;
import com.jorja.proyect.proyectogestoritvfinal.modelo.Sesion;
import com.jorja.proyect.proyectogestoritvfinal.modelo.Usuario;
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

import static com.jorja.proyect.proyectogestoritvfinal.controlador.Utils.*;
import static com.jorja.proyect.proyectogestoritvfinal.controlador.bbdd.CONEXIONBD.comprobarConexion;

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
        String sql = "SELECT * FROM datos_usuario du WHERE du.Correo = ? AND du.Contraseña = ?";

        cbd = new CONEXIONBD();
        conexion = cbd.abrirConexion();
        comprobarConexion(conexion);
        try {
            sentencia = conexion.prepareStatement(sql);
            sentencia.setString(1, txtEmail.getText());
            sentencia.setString(2, hashPassword(txtPassword.getText()));
            resultado = sentencia.executeQuery();

            if (txtEmail.getText().isEmpty() || txtPassword.getText().isEmpty()) {
                mostrarAlerta("Campos Vacíos", "¡Campos vacíos! Por favor, completa todos los campos antes de continuar.", Alert.AlertType.ERROR);
            } else if (resultado.next()) {
                String passwordUsuario = resultado.getString("Contraseña");
                if (passwordUsuario.equals(hashPassword(txtPassword.getText()))) {
                    boolean esAdministrador = resultado.getBoolean("administrador");
                    if (esAdministrador) {
                        // Obtener los datos del usuario
                        int idUsuario = resultado.getInt("ID");
                        String nombreUsuario = resultado.getString("Nombre");
                        String apellidoUsuario = resultado.getString("Apellido");
                        String telefonoUsuario = resultado.getString("Telefono");
                        String correoUsuario = resultado.getString("Correo");

                        // Crear una instancia de Usuario con los datos obtenidos
                        Usuario usuario = new Usuario(idUsuario, nombreUsuario, apellidoUsuario, telefonoUsuario, correoUsuario, passwordUsuario, false, "");

                        // Iniciar sesión con el usuario obtenido
                        Sesion.iniciarSesion(usuario);

                        // Cerrar esta ventana y abrir la ventana principal
                        txtEmail.getScene().getWindow().hide();
                        try {
                            new VentanaPrincipal().start(new Stage());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        // Si el usuario no es administrador
                        mostrarAlerta("Acceso Denegado", "No tienes permisos de administrador para acceder a esta aplicación.", Alert.AlertType.ERROR);
                    }
                } else {
                    mostrarAlerta("Error de Credenciales", "¡Error de credenciales! Por favor, verifica tus datos e inténtalo nuevamente.", Alert.AlertType.ERROR);
                }
            } else {
                mostrarAlerta("Error de Credenciales", "¡Error de credenciales! Por favor, verifica tus datos e inténtalo nuevamente.", Alert.AlertType.ERROR);
            }
        } catch (SQLException e) {
           mostrarAlerta("Error","Error en la conexión de la base de datos", Alert.AlertType.ERROR);
        } finally {
            cerrarConexion(cbd);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtPassword.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                iniciarSesion();
            }
        });
        txtEmail.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                iniciarSesion();
            }
        });
    }
}

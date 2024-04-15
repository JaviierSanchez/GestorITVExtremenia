package com.jorja.proyect.proyectogestoritvfinal.controlador;

import com.jorja.proyect.proyectogestoritvfinal.vista.VentanaPrincipal;
import com.jorja.proyect.proyectogestoritvfinal.vista.VentanaRegister;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

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
        String email = txtEmail.getText(); // Obtener el texto del campo de correo electrónico
        String password = txtPassword.getText(); // Obtener el texto del campo de contraseña

        if (email.isEmpty() || password.isEmpty()) {
            mostrarAlerta("Error", "Rellena los campos");
        } else if (!email.equals("admin@gmail.es") || !password.equals("admin")) {
            mostrarAlerta("Error", "Credenciales erróneas");
        } else {
            // Las credenciales son válidas, ocultar la ventana de inicio de sesión y mostrar la ventana principal
            txtEmail.getScene().getWindow().hide();
            try {
                new VentanaPrincipal().start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
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
}

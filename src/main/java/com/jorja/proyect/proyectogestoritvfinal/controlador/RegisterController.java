package com.jorja.proyect.proyectogestoritvfinal.controlador;

import com.jorja.proyect.proyectogestoritvfinal.vista.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {


    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtNombre;


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


    /*
     * Comprobar que los campos no estan vacios, si se cumple el formato de todos los campos, compruebas que las contraseñas son iguales,
     * si todo esta bien te dirije hacia la ventana login y ya puedes iniciar sesion
     *
     */

    /*

    INSERT INTO datos_usuario(Nombre,Apellido,Telefono,Correo,Contraseña,Administrador) values ("Jorge","Gomez",656745693,"jorgollo04@gmail.com","1234",False)

     */


    private void registrarUsuario() {


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

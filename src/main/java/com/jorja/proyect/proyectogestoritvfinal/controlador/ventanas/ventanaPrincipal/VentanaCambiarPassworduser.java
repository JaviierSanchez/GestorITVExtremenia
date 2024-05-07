package com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaPrincipal;

import com.jorja.proyect.proyectogestoritvfinal.controlador.bbdd.CONEXIONBD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VentanaCambiarPassworduser {

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;
    private CONEXIONBD cbd;
    @FXML
    private TextField txtPasswordAntigua;

    @FXML
    private TextField txtPasswordNueva;

    @FXML
    private TextField txtPasswordNueva2;

    @FXML
    void btnActualizar(ActionEvent event) {

        String sql = "UPDATE datos_usuario du SET du.Contraseaña = ? WHERE du.id = ? ";


    }

    @FXML
    void btnCancelar(ActionEvent event) {
        System.exit(0);

    }

}

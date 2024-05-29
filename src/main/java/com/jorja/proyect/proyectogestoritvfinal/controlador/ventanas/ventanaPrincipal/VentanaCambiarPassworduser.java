package com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaPrincipal;

import com.jorja.proyect.proyectogestoritvfinal.controlador.bbdd.CONEXIONBD;
import com.jorja.proyect.proyectogestoritvfinal.modelo.Sesion;
import com.jorja.proyect.proyectogestoritvfinal.modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.jorja.proyect.proyectogestoritvfinal.controlador.Utils.*;

public class VentanaCambiarPassworduser {

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;
    private CONEXIONBD cbd;
    @FXML
    private Label labelErrorPassword;

    @FXML
    private Label labelErrorPassword2;
    @FXML
    private TextField txtPasswordAntigua;

    @FXML
    private TextField txtPasswordNueva;

    @FXML
    private TextField txtPasswordNueva2;

    @FXML
    void btnActualizar(ActionEvent event) {

        Usuario usuario = Sesion.getUsuarioActual();

        // Consulta SQL para actualizar la contraseña del usuario
        String sql = "UPDATE datos_usuario SET Contraseña = ? WHERE id = ?";
        cbd = new CONEXIONBD();
        conexion = cbd.abrirConexion();

        // Verificar que los campos de contraseña no estén vacíos
        if (txtPasswordAntigua.getText().isEmpty() || txtPasswordNueva.getText().isEmpty() || txtPasswordNueva2.getText().isEmpty()) {
            mostrarAlerta("Error", "Rellena los campos", Alert.AlertType.ERROR);
        } else if (!txtPasswordNueva.getText().equals(txtPasswordNueva2.getText())) { // si no son iguales mostramos etiquetas de error
            labelErrorPassword.setVisible(true);
            labelErrorPassword2.setVisible(true);
        } else if (!validarPassword(txtPasswordNueva)) { // Validar la nueva contraseña
            return;
        } else {
            try {
                // Verificar la contraseña antigua del usuario
                String sqlVerificar = "SELECT Contraseña FROM datos_usuario WHERE id = ?";
                sentencia = conexion.prepareStatement(sqlVerificar);
                sentencia.setInt(1, usuario.getId());
                resultado = sentencia.executeQuery();

                if (resultado.next()) {
                    // Sacamos de la base de datos la contraseña del usuario
                    String passwordUsuario = resultado.getString("Contraseña");
                    // Comprobamos que la contraseña antigua es igual a la nueva contraseña que se quiere establecer
                    if (!passwordUsuario.equals(hashPassword(txtPasswordAntigua.getText()))) {
                        mostrarAlerta("Error", "La contraseña antigua es incorrecta", Alert.AlertType.ERROR);
                        return;
                    }
                }

                // Confirmar la actualización de la contraseña con una alerta
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Actualizar contraseña");
                alert.setContentText("¿Estás seguro que quieres actualizar la contraseña?");
                ButtonType opcion = alert.showAndWait().orElse(ButtonType.CANCEL);

                if (opcion == ButtonType.OK) {
                    // Guardamos en la variable la nueva contraseña cifrada
                    String hashedPassword = hashPassword(txtPasswordNueva.getText());

                    // Preparar la sentencia SQL para actualizar la contraseña
                    sentencia = conexion.prepareStatement(sql);
                    sentencia.setString(1, hashedPassword);
                    sentencia.setInt(2, usuario.getId());

                    // Ejecutar la actualización de la contraseña
                    int filas = this.sentencia.executeUpdate();

                    if (filas > 0) {
                        mostrarAlerta("Contraseña actualizada", "La contraseña ha sido actualizada exitosamente", Alert.AlertType.INFORMATION);
                        txtPasswordAntigua.getScene().getWindow().hide();
                    } else {
                        mostrarAlerta("Error", "No se pudo actualizar la contraseña", Alert.AlertType.ERROR);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                cbd.cerrarConexion();
            }
        }
    }


    @FXML
    void btnCancelar(ActionEvent event) {
        txtPasswordAntigua.getScene().getWindow().hide();
    }

}

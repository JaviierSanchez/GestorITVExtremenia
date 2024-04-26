package com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaPrincipal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class VentanaPrincipalControlador {

    @FXML
    private Button btnLateralCita;

    @FXML
    private Button btnLateralInicio;

    @FXML
    private Button btnLateralPerfil;

    @FXML
    private Button btnLateralVehiculo;

    @FXML
    private AnchorPane layoutInicio;

    @FXML
    private AnchorPane layoutPedirCita;

    @FXML
    private AnchorPane layoutPerfil;

    @FXML
    private AnchorPane layoutVehiculo;




    public void cambiarVentana(ActionEvent actionEvent) {

        if (actionEvent.getSource() == btnLateralInicio) {

            layoutInicio.setVisible(true);
            layoutPedirCita.setVisible(false);
            layoutPerfil.setVisible(false);
            layoutVehiculo.setVisible(false);

            btnLateralInicio.setStyle("-fx-background-color: #21666C; -fx-background-radius: 15px; -fx-cursor: hand;");
            btnLateralCita.setStyle("-fx-background-color: none; -fx-cursor: hand;");
            btnLateralVehiculo.setStyle("-fx-background-color: none; -fx-cursor: hand;");
            btnLateralPerfil.setStyle("-fx-background-color: none; -fx-cursor: hand;");


        } else if (actionEvent.getSource() == btnLateralCita) {
            layoutInicio.setVisible(false);
            layoutPedirCita.setVisible(true);
            layoutPerfil.setVisible(false);
            layoutVehiculo.setVisible(false);
            btnLateralInicio.setStyle("-fx-background-color: none;  -fx-cursor: hand;");
            btnLateralCita.setStyle("-fx-background-color: #21666C; -fx-background-radius: 15px; -fx-cursor: hand;");
            btnLateralVehiculo.setStyle("-fx-background-color: none; -fx-cursor: hand;");
            btnLateralPerfil.setStyle("-fx-background-color: none; -fx-cursor: hand;");

        }else if (actionEvent.getSource() == btnLateralVehiculo) {
            //Config layout
            layoutInicio.setVisible(false);
            layoutPedirCita.setVisible(false);
            layoutVehiculo.setVisible(true);
            layoutPerfil.setVisible(false);

            //Config style botones
            btnLateralInicio.setStyle("-fx-background-color: none; -fx-cursor: hand;");
            btnLateralCita.setStyle("-fx-background-color: none; -fx-cursor: hand;");
            btnLateralVehiculo.setStyle("-fx-background-color: #21666C; -fx-background-radius: 15px; -fx-cursor: hand;");
            btnLateralPerfil.setStyle("-fx-background-color: none; -fx-cursor: hand;");

        }else if (actionEvent.getSource() == btnLateralPerfil) {
            layoutInicio.setVisible(false);
            layoutPedirCita.setVisible(false);
            layoutVehiculo.setVisible(false);
            layoutPerfil.setVisible(true);

            btnLateralInicio.setStyle("-fx-background-color: none;  -fx-cursor: hand;");
            btnLateralCita.setStyle("-fx-background-color: none; -fx-cursor: hand;");
            btnLateralVehiculo.setStyle("-fx-background-color: none; -fx-cursor: hand;");
            btnLateralPerfil.setStyle("-fx-background-color: #21666C; -fx-background-radius: 15px; -fx-cursor: hand;");
        }
    }
}

package com.jorja.proyect.proyectogestoritvfinal.vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import static com.jorja.proyect.proyectogestoritvfinal.controlador.Utils.VENTANAREGISTER;

public class VentanaRegister extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        HBox root = FXMLLoader.load(getClass().getResource(VENTANAREGISTER));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }
}

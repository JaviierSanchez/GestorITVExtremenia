package com.jorja.proyect.proyectogestoritvfinal.vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static com.jorja.proyect.proyectogestoritvfinal.controlador.Utils.VENTANACAMBIARPASSWORD;

public class VentanaCambiarPassword extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        VBox root = FXMLLoader.load(getClass().getResource(VENTANACAMBIARPASSWORD));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
}

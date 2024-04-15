package com.jorja.proyect.proyectogestoritvfinal.vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static com.jorja.proyect.proyectogestoritvfinal.controlador.Utils.VENTANAPRINCIPAL;

public class VentanaPrincipal extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        VBox root = FXMLLoader.load(getClass().getResource(VENTANAPRINCIPAL));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }
}

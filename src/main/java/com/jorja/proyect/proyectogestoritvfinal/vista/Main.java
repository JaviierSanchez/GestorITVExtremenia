package com.jorja.proyect.proyectogestoritvfinal.vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static com.jorja.proyect.proyectogestoritvfinal.controlador.Utils.VENTANALOGIN;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        HBox root = FXMLLoader.load(getClass().getResource(VENTANALOGIN));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

module com.jorja.proyect.proyectogestoritvfinal {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jorja.proyect.proyectogestoritvfinal to javafx.fxml;
    opens com.jorja.proyect.proyectogestoritvfinal.controlador to javafx.fxml;

    exports com.jorja.proyect.proyectogestoritvfinal;
    exports com.jorja.proyect.proyectogestoritvfinal.vista;
    exports com.jorja.proyect.proyectogestoritvfinal.controlador;
}
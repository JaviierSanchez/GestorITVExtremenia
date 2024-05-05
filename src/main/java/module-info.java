module com.jorja.proyect.proyectogestoritvfinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.jorja.proyect.proyectogestoritvfinal.controlador to javafx.fxml;

    exports com.jorja.proyect.proyectogestoritvfinal.vista;
    exports com.jorja.proyect.proyectogestoritvfinal.controlador;
    exports com.jorja.proyect.proyectogestoritvfinal.controlador.bbdd;
    opens com.jorja.proyect.proyectogestoritvfinal.controlador.bbdd to javafx.fxml;
    exports com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaLogin;
    opens com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaLogin to javafx.fxml;
    exports com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaRegister;
    opens com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaRegister to javafx.fxml;
    exports com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaPrincipal;
    opens com.jorja.proyect.proyectogestoritvfinal.controlador.ventanas.ventanaPrincipal to javafx.fxml;
    opens com.jorja.proyect.proyectogestoritvfinal.modelo to javafx.base;
}
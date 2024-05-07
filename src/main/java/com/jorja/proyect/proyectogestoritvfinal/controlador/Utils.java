package com.jorja.proyect.proyectogestoritvfinal.controlador;

public class Utils {

    // Ruta archivos fxml
    public static final String VENTANALOGIN = "/com/jorja/proyect/proyectogestoritvfinal/vista/VentanaLogueo.fxml";
    public static final String VENTANAPRINCIPAL = "/com/jorja/proyect/proyectogestoritvfinal/vista/VentanaPrincipal.fxml";
    public static final String VENTANAREGISTER = "/com/jorja/proyect/proyectogestoritvfinal/vista/VentanaRegister.fxml";
    public static final String VENTANACAMBIARPASSWORD = "/com/jorja/proyect/proyectogestoritvfinal/vista/VentanaCambiarPassworduser.fxml";

    // Comprobaciones textos
    public static final String TELEFONOREGEX = "\\d{9}";
    public static final String CORREOREGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
    public static final String PASSWORDREGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

    // Columnas USuario
    public static final String COLUMNIDUSUARIO = "id";
    public static final String COLUMNNOMBREUSUARIO = "nombre";
    public static final String COLUMNAPELLIDOUSUARIO = "apellido";
    public static final String COLUMNTELEFONOUSUARIO = "telefono";
    public static final String COLUMNCORREOUSUARIO = "correo";
    public static final String COLUMNCONTRASEÑAUSUARIO = "contraseña";
    public static final String COLUMNADMINISTRADORUSUARIO = "administrador";
    public static final String COLUMNFECHAALTAUSUARIO = "FechaAlta";

}

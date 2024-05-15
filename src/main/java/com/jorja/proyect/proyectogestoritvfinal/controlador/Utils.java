package com.jorja.proyect.proyectogestoritvfinal.controlador;

import com.jorja.proyect.proyectogestoritvfinal.controlador.bbdd.CONEXIONBD;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    private static Connection conexion;
    private static PreparedStatement sentencia;
    private static ResultSet resultado;

    // Ruta archivos fxml
    public static final String VENTANALOGIN = "/com/jorja/proyect/proyectogestoritvfinal/vista/VentanaLogueo.fxml";
    public static final String VENTANAPRINCIPAL = "/com/jorja/proyect/proyectogestoritvfinal/vista/VentanaPrincipal.fxml";
    public static final String VENTANAREGISTER = "/com/jorja/proyect/proyectogestoritvfinal/vista/VentanaRegister.fxml";
    public static final String VENTANACAMBIARPASSWORD = "/com/jorja/proyect/proyectogestoritvfinal/vista/VentanaCambiarPassworduser.fxml";

    // Comprobaciones textos
    public static final String TELEFONOREGEX = "\\d{9}";
    public static final String CORREOREGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
    public static final String PASSWORDREGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    public static final String MATRICULAREGEX = "(?i)^(\\d{4}?[ -]*[A-Z]{3}|[A-Z]{1,2}[ -]*\\d{4}?[ -]*[A-Z]{1,2})$";

    // Columnas Usuario
    public static final String COLUMNIDUSUARIO = "id";
    public static final String COLUMNNOMBREUSUARIO = "nombre";
    public static final String COLUMNAPELLIDOUSUARIO = "apellido";
    public static final String COLUMNTELEFONOUSUARIO = "telefono";
    public static final String COLUMNCORREOUSUARIO = "correo";
    public static final String COLUMNCONTRASEÑAUSUARIO = "contraseña";
    public static final String COLUMNADMINISTRADORUSUARIO = "administrador";
    public static final String COLUMNFECHAALTAUSUARIO = "FechaAlta";

    // Columnas Vehiculo

    public static final String COLUMNMATRICULAVEHICULO = "matricula";
    public static final String COLUMNMARCAVEHICULO = "marca";
    public static final String COLUMNMODELOVEHICULO = "modelo";
    public static final String COLUMNAÑOVEHICULO = "year";
    public static final String COLUMNTIPOVEHICULOVEHICULO = "tipoVehiculo";
    public static final String COLUMNPROPIETARIOVEHICULO = "correoUsuario";


    // Columnas Cita
    public static final String COLUMNIDCITA = "id";
    public static final String COLUMNMATRICULAVEHICULOCITA = "matriculaVehiculo";
    public static final String COLUMNFECHACITA = "Fecha";
    public static final String COLUMNHORACITA = "Hora";
    public static final String COLUMNTIPOINSPECCIONIDCITA = "TipoInspeccionId";
    public static final String COLUMNTIPOVEHICULOIDCITA = "TipoVehiculoId";
    public static final String COLUMNPRECIOCITA = "precio";
    public static final String COLUMNACTIVACITA = "activa";


    // Obtener la fecha actual
    public static String obtenerFechaActual() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    // VALIDACIONES DATOS


    //Validar Telefono
    public static boolean validarTelefono(TextField textField) {
        if (!textField.getText().matches(TELEFONOREGEX)) {
            mostrarAlerta("Error", "Teléfono no válido. Debe tener 9 dígitos. \n Ejemplo: 123456789", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    // Validar Correo
    public static boolean validarCorreo(TextField textField){
        if(!textField.getText().matches(CORREOREGEX)){
            mostrarAlerta("Error", "El correo electrónico ingresado no es válido.", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    // Validar Password
    public static boolean validarPassword(TextField textField) {
        if (!textField.getText().matches(PASSWORDREGEX)) {
            mostrarAlerta("Error", "La contraseña debe tener al menos 8 caracteres y contener al menos una letra y un número.", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    // Metodo para validar Matricula
    public static boolean validarMatricula(TextField textField){
        if(!textField.getText().matches(MATRICULAREGEX)){
            mostrarAlerta("Error","La matricula no cumple el formato. \n Ejemplo: 1234 ABC", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }
    public static boolean validarAño(TextField textField) {

        int añoActual = Calendar.getInstance().get(Calendar.YEAR);

        String añoTexto = textField.getText();

        try {
            int añoInt = Integer.parseInt(añoTexto);
            if (añoInt >= 1900 && añoInt <= añoActual) {
                return true;
            } else {
                mostrarAlerta("Error", "El año debe estar entre 1900 y el año actual.", Alert.AlertType.ERROR);
                return false;
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Formato de año inválido.", Alert.AlertType.ERROR);
            return false;
        }
    }

    // Mostrar alertas
    public static void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipoAlerta) {
        Alert alert = new Alert(tipoAlerta);
        alert.setHeaderText(null);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    //Cerrar conexiones
      public static void cerrarConexion(CONEXIONBD cbd) {
        try {
            if (resultado != null) resultado.close();
            if (sentencia != null) sentencia.close();
            if (conexion != null) {
                cbd.cerrarConexion();
                conexion.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // Método para obtener el nombre del mes a partir del número del mes
    public static String obtenerNombreMes(int numeroMes) {
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] nombresMeses = dfs.getMonths();
        return nombresMeses[numeroMes - 1];
    }



    // Metodo Cifrado de contraseña mediante SHA-512
    public static String hashPassword(String password) {
        try {
            // Usamos SHA-512 para el hash
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            // Convertir el hash a formato hexadecimal
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
